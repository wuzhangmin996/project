package com.wu.project20.Controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.wu.project20.Mapper.ScoreDao;
import com.wu.project20.Mapper.StudentMapper;
import com.wu.project20.Mapper.TeacherMapper;
import com.wu.project20.Result.*;
import com.wu.project20.Token.TokenTools;
import com.wu.project20.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    ScoreDao scoreDao ;

    @Autowired
    StudentMapper studentMapper;

    TokenTools tokenTools = new TokenTools();

    @RequestMapping(method = RequestMethod.POST, value = "user/questionlist")
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
    @ResponseBody
    public ResultChoiceQuestionList getChoiceQuestionList(HttpServletRequest request, @RequestBody Htmldata htmldata) {
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id = jwt.getClaim("id").asString();
        Meta meta = new Meta();
        int pagesize = 10;
        int page = Integer.parseInt(htmldata.getPagenum());
        if (teacherMapper.SelectTeacherToken(id).equals(token)) {
            meta.setStatus(200);
            meta.setMessage("获取成功");
            return new ResultChoiceQuestionList(teacherMapper.selectTotalPage("%" + htmldata.getChoicename() + "%"), htmldata.getPagenum(), teacherMapper.questionPageList("%" + htmldata.getChoicename() + "%", page, pagesize), meta);
        }
        meta.setStatus(400);
        meta.setMessage("获取失败");
        return new ResultChoiceQuestionList(0, null, null, meta);
    }

    /*获取一套试卷的详细信息*/
    @RequestMapping(method = RequestMethod.GET, value = "user/getexaminfo")
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
    @ResponseBody
    public ResultExam getExamInfo(HttpServletRequest request, @RequestParam("examid") String examid) {
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id = jwt.getClaim("id").asString();
        Meta meta = new Meta();
        String role = jwt.getClaim("role").asString();
        if (role.equals("2")) {
            if (teacherMapper.SelectTeacherToken(id).equals(token)) {
                meta.setMessage("获取成功");
                meta.setStatus(200);
                return new ResultExam(0, teacherMapper.getExamInfo(examid), meta);
            }
            meta.setMessage("获取失败");
            meta.setStatus(400);
            return new ResultExam(0, null, meta);
        }

        meta.setMessage("禁止访问");
        meta.setStatus(403);
        return new ResultExam(0, null, meta);
    }

    /*
     * 更新试卷的信息*/
    @RequestMapping(method = RequestMethod.POST, value = "user/updateexaminfo")
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
    @ResponseBody
    public ResultExam updateExamInfo(HttpServletRequest request, @RequestBody Htmldata htmldata) {
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id = jwt.getClaim("id").asString();
        Meta meta = new Meta();
        String role = jwt.getClaim("role").asString();
        if (role.equals("2")) {
            if (teacherMapper.SelectTeacherToken(id).equals(token)) {
                Exam exam = new Exam();
                exam.setExamid(htmldata.getExamid());
                exam.setExamname(htmldata.getExamname());

                Date[] dates = htmldata.getExamtime();
                exam.setExamendtime(dates[1]);
                exam.setExamstarttime(dates[0]);
                teacherMapper.updateExamInfo(exam);
                meta.setMessage("修改成功");
                meta.setStatus(200);
                return new ResultExam(0, null, meta);
            }
            meta.setMessage("修改失败");
            meta.setStatus(400);
            return new ResultExam(0, null, meta);
        }
        meta.setMessage("禁止访问");
        meta.setStatus(403);
        return new ResultExam(0, null, meta);
    }

    /*
     * 新增选择题*/
    @RequestMapping(method = RequestMethod.POST, value = "user/addchoice")
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
    @ResponseBody
    public ResultExam InsertChoiceQuestion(HttpServletRequest request, @RequestBody choiceRecord question) {
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id = jwt.getClaim("id").asString();
        Meta meta = new Meta();
        String role = jwt.getClaim("role").asString();
        if (role.equals("2")) {
            if (teacherMapper.SelectTeacherToken(id).equals(token)) {
                int choiceQuestionId = teacherMapper.selectBeforeChoiceQuestionId() + 1;
                question.setChoicequestionid(choiceQuestionId);
                teacherMapper.InsertChoice(question);
                meta.setStatus(200);
                meta.setMessage("插入成功");
                return new ResultExam(0, null, meta);
            }
            meta.setStatus(400);
            meta.setMessage("获取失败");
            return new ResultExam(0, null, meta);
        }
        meta.setStatus(403);
        meta.setMessage("获取失败");
        return new ResultExam(0, null, meta);
    }

    /*
     * 新增一套试卷*/
    @RequestMapping(method = RequestMethod.POST, value = "user/addexam")
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
    @ResponseBody
    public ResultExam createExam(HttpServletRequest request, @RequestBody Htmldata htmldata) throws ParseException {
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id = jwt.getClaim("id").asString();
        Meta meta = new Meta();
        String role = jwt.getClaim("role").asString();
        Exam exam = new Exam();
        Date date = new Date();
        String[] choices = htmldata.getSelectid().split("\\D+");
        String choicelist = "";
        for (int i = 1; i < choices.length; i = i + 2) {
            if (i == choices.length - 1) {
                choicelist = choicelist + choices[i];
            } else {
                choicelist = choicelist + choices[i] + "-";
            }
        }
        exam.setExamname(htmldata.getExamname());
        exam.setChoicequestionid(choicelist);
        exam.setExamcreatetime(date);
        Date[] dates = htmldata.getExamtime();
        exam.setExamendtime(dates[1]);
        exam.setExamstarttime(dates[0]);
        if (role.equals("2")) {
            if (teacherMapper.SelectTeacherToken(id).equals(token)) {
                int examid = teacherMapper.getLastExamid();
                exam.setExamid(examid + 1);
                exam.setTid(id);
                exam.setExamscore(String.valueOf(htmldata.getExamscore()));
                teacherMapper.createExam(exam);
                meta.setStatus(200);
                meta.setMessage("创建成功");
                return new ResultExam(0, null, meta);
            }
            meta.setStatus(400);
            meta.setMessage("创建失败");
            return new ResultExam(0, null, meta);
        }
        meta.setStatus(403);
        meta.setMessage("获取失败");
        return new ResultExam(0, null, meta);
    }

    /*
     * 获取一道题的详细信息*/
    @RequestMapping(method = RequestMethod.GET, value = "user/getchoiceinfo")
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
    @ResponseBody
    public ResultChoice getChoiceInfo(HttpServletRequest request, @RequestParam("id") String questionid) {
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id = jwt.getClaim("id").asString();
        Meta meta = new Meta();
        String role = jwt.getClaim("role").asString();
        if (role.equals("2")) {
            if (teacherMapper.SelectTeacherToken(id).equals(token)) {
                meta.setStatus(200);
                meta.setMessage("获取成功");
                return new ResultChoice(teacherMapper.getChoiceInfo(questionid), meta);
            }
            meta.setStatus(400);
            meta.setMessage("获取失败");
            return new ResultChoice(null, meta);
        }
        meta.setStatus(403);
        meta.setMessage("获取失败");
        return new ResultChoice(null, meta);
    }

    @RequestMapping(method = RequestMethod.POST, value = "user/updatechoiceinfo")
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
    @ResponseBody
    public ResultExam updateChoiceInfo(HttpServletRequest request, @RequestBody choiceRecord choiceRecord) {
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id = jwt.getClaim("id").asString();
        Meta meta = new Meta();
        String role = jwt.getClaim("role").asString();
        if (role.equals("2")) {
            if (teacherMapper.SelectTeacherToken(id).equals(token)) {
                teacherMapper.updateChoiceInfo(choiceRecord);
                meta.setMessage("修改成功");
                meta.setStatus(200);
                return new ResultExam(0, null, meta);
            }
            meta.setMessage("修改失败");
            meta.setStatus(400);
            return new ResultExam(0, null, meta);
        }
        meta.setMessage("禁止访问");
        meta.setStatus(403);
        return new ResultExam(0, null, meta);
    }

    @RequestMapping(method = RequestMethod.GET, value = "user/deleteexam")
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
    @ResponseBody
    public ResultExam deleteExam(HttpServletRequest request, @RequestParam("examid") int examid) {
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id = jwt.getClaim("id").asString();
        Meta meta = new Meta();
        String role = jwt.getClaim("role").asString();
        if (role.equals("2")) {
            if (teacherMapper.SelectTeacherToken(id).equals(token)) {
                teacherMapper.deleteOperation(examid);
                teacherMapper.deleteChoiceInfo(examid);
                teacherMapper.deleteScore(examid);
                teacherMapper.deleteExam(examid);
                meta.setMessage("修改成功");
                meta.setStatus(200);
                return new ResultExam(0, null, meta);
            }
            meta.setMessage("修改失败");
            meta.setStatus(400);
            return new ResultExam(0, null, meta);
        }
        meta.setMessage("禁止访问");
        meta.setStatus(403);
        return new ResultExam(0, null, meta);
    }

    @RequestMapping(method = RequestMethod.GET, value = "user/gradeanalysis")
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
    @ResponseBody
    public ResultStudentAnalysis deleteExam(HttpServletRequest request, @RequestParam("examid") String examid) {
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id = jwt.getClaim("id").asString();
        Meta meta = new Meta();
        String role = jwt.getClaim("role").asString();

        if (role.equals("2")) {
            if (teacherMapper.SelectTeacherToken(id).equals(token)) {
                if(teacherMapper.getchoiceNum(examid)>0){
                    List<StudentAnalysis> studentAnalyses = new ArrayList<>();
                    String[] studentid = studentMapper.selectidfromchoicescore(examid);
                    StudentAnalysis student = new StudentAnalysis() ;
                    int totalScore = 0;
                    Score score1 = null;
                    int operationScore =0 ;
                    double averagescore =  0 ;
                    for(int i = 0 ; i < studentid.length ; i++ ){
                        student  =  studentMapper.getStudentScoreInfo(Integer.parseInt(examid),studentid[i]);
                        if (scoreDao.existScore(id)==1){
                            score1= scoreDao.SelectScoreById(id);
                            operationScore = score1.getScore1()+score1.getScore2()+score1.getScore6()+score1.getScore8()+
                                    score1.getScore3()+score1.getScore4()+score1.getScore5();
                        }
                        totalScore = operationScore+ Integer.parseInt(studentMapper.selectChoiceScore(examid,student.getSid() ));
                        averagescore = averagescore + totalScore ;
                        student.setScore(totalScore);
                        studentAnalyses.add(student);
                    }


                    meta.setMessage("访问成功");
                    meta.setStatus(200);
                    return new ResultStudentAnalysis(studentMapper.getTitle(Integer.parseInt(examid)),studentAnalyses,meta,String.valueOf(averagescore/studentid.length));

                }
                meta.setMessage("访问失败");
                meta.setStatus(403);
                return new ResultStudentAnalysis(null,null,meta ,null);

            }
            meta.setMessage("访问失败");
            meta.setStatus(400);
            return new ResultStudentAnalysis(null,null,meta ,null);
        }
        meta.setMessage("禁止访问");
        meta.setStatus(403);
        return new ResultStudentAnalysis(null,null,meta ,null);
    }
    @PostMapping(value = "user/addtrain")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultMeta AddTrain (@RequestBody AddTrain addTrain, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id = jwt.getClaim("id").asString();
        String choice = null;
        for(int i = 0 ; i<addTrain.getSelectdata().size();i++){
            if(i==0){
                choice = addTrain.getSelectdata().get(i).getChoicequestionid()+"-"+addTrain.getSelectdata().get(i).getTimelong();
            }
            else {
                choice=choice + " - "+addTrain.getSelectdata().get(i).getChoicequestionid()+"-"+addTrain.getSelectdata().get(i).getTimelong();
            }
        }
        System.out.println(choice);
        Date date = new Date();
        int trainid = teacherMapper.getLastTrain()+1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(trainid+addTrain.getTrainname()+addTrain.getTrainstarttime()+id+date+choice+addTrain.getTrainscore());
        teacherMapper.addTrain(trainid,addTrain.getTrainname(),sdf.format(addTrain.getTrainstarttime()),id,date,choice,addTrain.getTrainscore());
        Meta meta = new Meta();
        meta.setStatus(200);
        meta.setMessage("新建成功");
        return new ResultMeta(meta);
    }
}
