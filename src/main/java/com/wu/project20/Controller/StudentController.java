package com.wu.project20.Controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.wu.project20.Mapper.ScoreDao;
import com.wu.project20.Mapper.StudentMapper;
import com.wu.project20.Result.ResultChoiceRecord;
import com.wu.project20.Result.ResultListStudent;
import com.wu.project20.Result.ResultStudent;
import com.wu.project20.Token.TokenTools;
import com.wu.project20.bean.*;
import com.wu.project20.unZip.UnZip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
* 在登录的时候，生成token,然后在进行其他操作时，都必须要验证token*/
@RestController
public class StudentController{

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    ScoreDao scoreDao ;

    TokenTools tokenTools = new TokenTools() ;

    /*
    * 上传文件，然后对上传*/
    @RequestMapping(method =  RequestMethod.POST ,value = "user/upload")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultStudent fileUpLoad(@RequestParam("file") MultipartFile file, Map<String, Object> map,
                                    HttpServletRequest request) throws Exception {
        String  token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id  =  jwt.getClaim("id").asString();
        Meta meta = new Meta();
        if(token.trim().equals(studentMapper.SelectToken(id))){
            String fileName=file.getOriginalFilename();
            //String fileNameSuffix=fileName.substring(fileName.lastIndexOf(".") + 1);
            //long fileSize=file.getSize();//字节数
            String filePath="C:\\Users\\Administrator\\Desktop\\upload\\";
            File fileSaving = new File(filePath + fileName);

            try {
                file.transferTo(fileSaving);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //System.out.println(id);
            //System.out.println(fileNameSuffix);

            File file1=new File(filePath + fileName);
            UnZip.deCompress(file1,filePath+id);
            map.put("filename",fileName);


            meta.setMessage("上传成功");
            meta.setStatus(200);
            return new ResultStudent(null,meta);
        }
        meta.setStatus(400);
        meta.setMessage("评判失败");
        return new ResultStudent(null,meta);
    }


    /*
       通过split函数进行分割，保留题号和选择的答案。再通过token反解出id值，
       要通过题号查出来的答案进行对比并且分出分数。 再把分数插入数据库* */
     @RequestMapping(method = RequestMethod.POST ,value = "user/submitexam"  )
     @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
     @ResponseBody
     public ResultStudent ChoiceScore(HttpServletRequest request ,@RequestBody StudentHtml studentHtml){
         String  token = request.getHeader("Authorization");
         DecodedJWT jwt = tokenTools.decodeToken(token);
         String id  =  jwt.getClaim("id").asString();
         Meta meta = new Meta();
         double score = 0  ;
         Date date =  new Date();
         if (token.trim().equals(studentMapper.SelectToken(id).trim())) {
             if(studentMapper.selectrecordchoice(studentHtml.getExamid(),id)==0) {
                 studentMapper.insertChoiceRecord(studentHtml.getExamid(), id, studentHtml.getStuChoice(), date);
                 if (!studentMapper.getChioce(id, studentHtml.getExamid()).isEmpty()) {
                     String[] questionId = studentMapper.getChioce(id, studentHtml.getExamid()).split("\\W+");
                     for (int i = 0; i < questionId.length; i = i + 2) {
                         if (questionId[i + 1].equals(studentMapper.selectanswer(Integer.parseInt(questionId[i])))) {
                             score = score + Double.parseDouble(studentMapper.selectscore(Integer.parseInt(questionId[i])));
                         }

                     }
                     studentMapper.insertChoiceScore(studentHtml.getExamid(), id, score);
                     String filePath= "C:\\Users\\Administrator\\Desktop\\upload\\"+id+"\\学生信息.bak";
                     File file = new File(filePath);
                     if(file.exists()){
                         scoreDao.callback(Integer.valueOf(id));
                     }
                     meta.setMessage("交卷成功");
                     meta.setStatus(200);
                     return new ResultStudent(null, meta);
                 }

             }
             meta.setMessage("交卷重复");
             meta.setStatus(403);
             return new ResultStudent(null,meta);

         }
         meta.setMessage("交卷失败");
         meta.setStatus(400);
         return new ResultStudent(null,meta);
     }
     /*
    *查询做过的记录,返回学生做过的记录，
    * */
    @RequestMapping(method = RequestMethod.GET,value = "user/gradedetail")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultChoiceRecord getChoiceRecord(HttpServletRequest request, @RequestParam("id") String examid ) {
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id = jwt.getClaim("id").asString();
        Meta meta = new Meta();
        if (token.trim().equals(studentMapper.SelectToken(id))) {
            if(studentMapper.selectrecordchoice(Integer.parseInt(examid),id ) !=0){
                if(!studentMapper.getChioce(id,Integer.parseInt(examid)).isEmpty()) {
                    List<choiceRecord>  choiceRecordList = new ArrayList<choiceRecord>();
                    String[] questionId = studentMapper.getChioce(id, Integer.parseInt(examid)).split("\\W+");
                    String[] choice = new String[questionId.length / 2];
                    for (int i = 0; i < questionId.length; i = i + 2) {
                        choice[i/2] = questionId[i + 1];
                        choiceRecord choiceRecord = studentMapper.getChoiceRecord(Integer.parseInt(questionId[i]));
                        choiceRecord.setChoice(choice[i/2]);
                        choiceRecordList.add(choiceRecord);
                    }
                    meta.setMessage("查询成功");
                    meta.setStatus(200);
                    int totalScore = 0 ;
                    Score score1 = null;
                    int operationScore =0 ;
                    if (scoreDao.existScore(id)==1){
                        score1= scoreDao.SelectScoreById(id);
                        operationScore = score1.getScore1()+score1.getScore2()+score1.getScore6()+score1.getScore8()+
                                score1.getScore3()+score1.getScore4()+score1.getScore5();
                    }

                    totalScore = operationScore+Integer.parseInt(studentMapper.selectChoiceScore(examid,id));
                    Scores scores =  new Scores() ;
                    scores.setChoiceScore(studentMapper.selectChoiceScore(examid,id));
                    scores.setOperationScore(String.valueOf(operationScore));
                    scores.setTotalScore(String.valueOf(totalScore));
                    List<Scores> scoresList = new ArrayList<>();
                    scoresList.add(scores);
                    List<Time> timeList =  new ArrayList<>();
                    Time time = studentMapper.getTime(Integer.parseInt(examid),id);
                    time.setSpendtime(time.getExamstarttime(),time.getSubmittime());
                    timeList.add(time);
                    return new ResultChoiceRecord(studentMapper.getTitle(Integer.parseInt(examid)),timeList,choiceRecordList,meta,scoresList,studentMapper.getExamTotalScore(Integer.parseInt(examid)));
                }
            }

        }
        meta.setMessage("查询失败");
        meta.setStatus(404);
        return new ResultChoiceRecord(null,null,null,meta,null,null);
    }


    /*
  删除一条学生记录,id为学生的学号
     */
    @RequestMapping(method = RequestMethod.POST,value = "user/delete")
    @ResponseBody
    public ResultStudent deleteUser(HttpServletRequest request){
        Meta meta = new Meta();
        meta.setMessage("删除成功");
        meta.setStatus(204);
        studentMapper.deletestudent(request.getParameter("id"));
        return new ResultStudent(null,meta);
    }

    @GetMapping("student/select_name/{name}&&{page}&&{pageSize}")//通过相似查询，查找学生，并且进行分页
    public List<Student> Select_Name_student(@PathVariable("name")String name,@PathVariable("page") int page ,
                                             @PathVariable("pageSize") int pageSize) {
        return studentMapper.select_name_student(name,page,pageSize);
    }
    /*
    * 分页查询，传入页数和每页显示的数量，进行查询*/
    @RequestMapping(method = RequestMethod.POST,value = "user/page")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultListStudent Select_page_Student(HttpServletRequest request){
        int page = Integer.parseInt(request.getParameter("page").trim());
        int pageSize = Integer.parseInt(request.getParameter("pageSize").trim());
        Meta meta = new Meta();
        meta.setMessage("查询成功");
        meta.setStatus(200);
        return new ResultListStudent(studentMapper.select_page_student(page,pageSize),meta);//分页查询。
    }

    @RequestMapping(method = RequestMethod.POST,value = "insert/student")
    @ResponseBody //插入一条学生记录
    public void insertStudent(HttpServletRequest request,@RequestBody Student student){
        studentMapper.insertstudent(student);
    }

}
