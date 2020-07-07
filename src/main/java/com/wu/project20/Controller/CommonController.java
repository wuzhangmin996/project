package com.wu.project20.Controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.wu.project20.Mapper.ManagerMapper;
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
public class CommonController {
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    ScoreDao scoreDao ;

    @Autowired
    TeacherMapper teacherMapper ;

    @Autowired
    ManagerMapper managerMapper ;

    TokenTools tokenTools = new TokenTools() ;

    /*
     * 学生登录，username为学号，password为密码，生成token，
     * 用学号+密码+时间生成token。存进数据库里面，成为验证的一项重大工具
     * */
    @RequestMapping(method = RequestMethod.POST , value = "user/login")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultToken studentLogin(HttpServletRequest request, @RequestBody StudentHtml studentHtml){

        String username =  studentHtml.getUsername();
        String password =  studentHtml.getPassword();/*
        String username =  request.getParameter("username");
        String password =  request.getParameter("password");*/
        Meta meta = new Meta();
        int role = 0;
        if(studentMapper.studentExist(username)==1){
            role = 1;
            if(password.trim().equals(studentMapper.getPassword(username).trim())){
                String token = tokenTools.createToken(username,password,String.valueOf(role));
                studentMapper.CreateToken(token,username);//将token传到数据库，进行保存。
                meta.setMessage("登录成功");
                meta.setStatus(200);
                return new ResultToken(token,meta);
            }
        }
        if (teacherMapper.teacherExist(username)==1) {
            role = 2;
            if (password.trim().equals(teacherMapper.getPassword(username).trim())) {
                String token = tokenTools.createToken(username, password, String.valueOf(role));
                teacherMapper.CreateToken(token, username);//将token传到数据库，进行保存。
                meta.setMessage("登录成功");
                meta.setStatus(200);
                return new ResultToken(token, meta);
            }
        }
        if (managerMapper.managerExist(username)==1) {
            role = 3;
            if (password.trim().equals(managerMapper.getPassword(username).trim())) {
                String token = tokenTools.createToken(username, password, String.valueOf(role));
                managerMapper.CreateToken(token, username);//将token传到数据库，进行保存。
                meta.setMessage("登录成功");
                meta.setStatus(200);
                return new ResultToken(token, meta);
            }
        }
        meta.setMessage("登录失败");
        meta.setStatus(400);
        return new ResultToken(null,meta) ;
    }
    @RequestMapping(method = RequestMethod.GET ,value = "user/menulist")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultMenu getMenu(HttpServletRequest request){
        String  token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id  =  jwt.getClaim("id").asString();
        String role =  jwt.getClaim("role").asString();
        Meta meta = new Meta();
        if(role.equals("1")){
            if(token.trim().equals(studentMapper.SelectToken(id))) {
                meta.setMessage("获取成功");
                meta.setStatus(200);
                return new ResultMenu(getMenuList(role),meta);
            }
        }
        else if(token.trim().equals(teacherMapper.SelectTeacherToken(id))) {
            meta.setMessage("获取成功");
            meta.setStatus(200);
            return new ResultMenu(getMenuList(role),meta);
        }

        return null;
    }
    /*
     * 通过点击个人信息一栏，通过传入的token，进行解码，解出id，
     * 查询出id所对应的token，看是否相等，等，则将个人信息进行返回。*/
    @RequestMapping(method = RequestMethod.GET ,value = "user/getpersonalinfo")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultInformation studentInformation(HttpServletRequest request){
        String  token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id  =  jwt.getClaim("id").asString();
        String role =  jwt.getClaim("role").asString();
        Meta meta = new Meta();
        if(role.equals("1")){
            if(token.trim().equals(studentMapper.SelectToken(id))){
                meta.setMessage("获取成功");
                meta.setStatus(200);
                return new ResultInformation(studentMapper.SelectID(id),null,null,meta);
            }
        }else if(role.equals("2")){
            if(token.trim().equals(teacherMapper.SelectTeacherToken(id))){
                meta.setMessage("获取成功");
                meta.setStatus(200);
                return new ResultInformation(null,teacherMapper.SelectTeacher(id),null,meta);
            }
        }/*else(role.equals("3")){
            if(token.trim().equals(managerMapper.SelectManagerToken(id))){

            }
        }*/

        meta.setMessage("获取失败");
        meta.setStatus(400);
        return new ResultInformation(null,null,null,meta);
    }
    /*
     * 修改密码
     * 通过进行对token的验证，利用反解出的id，进行对密码的修改*/
    @RequestMapping(method = RequestMethod.POST , value = "user/updatepassword")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultStudent updateStudent(HttpServletRequest request, @RequestBody StudentHtml studentHtml ){
        String  token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id  =  jwt.getClaim("id").asString();
        String password = jwt.getClaim("password").asString();
        String role = jwt.getClaim("role").asString() ;
        String oldPassword = studentHtml.getOldPassword();
        Meta meta = new Meta();
        if(role.equals("1")){
            if(token.trim().equals(studentMapper.SelectToken(id))){
                if(password.equals(oldPassword)){
                    String newPassword =  studentHtml.getNewPassword();
                    /*String password =  studentHtml.getPassword*/
                    studentMapper.updatestudent(newPassword,id);
                    meta.setMessage("修改成功");
                    meta.setStatus(200);
                    return new ResultStudent(null,meta);
                }
                meta.setMessage("修改失败");
                meta.setStatus(403);
                return new ResultStudent(null,meta);
            }
        }
        if(role.equals("2")){
            if(token.trim().equals(teacherMapper.SelectTeacherToken(id))){
                if(password.equals(oldPassword)){
                    String newPassword =  studentHtml.getNewPassword();
                    /*String password =  studentHtml.getPassword*/
                    teacherMapper.updateTeacher(id,newPassword);
                    meta.setMessage("修改成功");
                    meta.setStatus(200);
                    return new ResultStudent(null,meta);
                }
                meta.setMessage("修改失败");
                meta.setStatus(403);
                return new ResultStudent(null,meta);

            }
        }

        meta.setMessage("修改失败");
        meta.setStatus(500);
        return new ResultStudent(null,meta);
    }
    /*
     * 通过试卷的名字相似查询*/
    @RequestMapping(method = RequestMethod.POST, value = "user/examlist")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultExam getExamListByLike(HttpServletRequest request , @RequestBody Htmldata  htmldata ){
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id = jwt.getClaim("id").asString();
        String role = jwt.getClaim("role").asString();
        Meta meta = new Meta();
        int pagesize = 10 ;
        int page = Integer.parseInt(htmldata.getPagenum());
        String examName = htmldata.getExamname();
        if(role.equals("1")){
            if (token.trim().equals(studentMapper.SelectToken(id).trim())) {
                meta.setMessage("获取成功");
                meta.setStatus(200);
                return new ResultExam(studentMapper.totalExam("%"+htmldata.getChoicename()+"%"),studentMapper.getExamListByLike("%" + examName + "%",page,pagesize), meta);
            }
            meta.setMessage("获取失败");
            meta.setStatus(400);
            return new ResultExam(0,null,meta);
        }
        if(role.equals("2")){
            if (token.trim().equals(teacherMapper.SelectTeacherToken(id).trim())) {
                meta.setMessage("获取成功");
                meta.setStatus(200);
                return new ResultExam(studentMapper.totalExam("%"+htmldata.getExamname()+"%"), studentMapper.getExamListByLike("%" + examName + "%",page,pagesize), meta);
            }
            meta.setMessage("获取失败");
            meta.setStatus(400);
            return new ResultExam(0,null,meta);
        }
        meta.setMessage("禁止访问");
        meta.setStatus(403);
        return new ResultExam(0,null,meta);
    }
    /*
     * 通过试卷的id的*/
    @RequestMapping(method = RequestMethod.GET, value = "user/getexam")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultQuestion getQuestionList(HttpServletRequest request , @RequestParam("id")  String eid) {
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id = jwt.getClaim("id").asString();
        String role = jwt.getClaim("role").asString();
        int examid = Integer.parseInt(eid);
        Meta meta = new Meta();
        Date date =  new Date();
        if (role.equals(("1"))) {
            if(date.before(studentMapper.selectEndTime(examid))&&date.after(studentMapper.selectStartTime(examid))){
                meta.setMessage("查询成功");
                meta.setStatus(200);
                String[] choiceId = studentMapper.selectQuestionId(examid).split("-");
                question question =  new question() ;
                List<question> questionList = new ArrayList<>();
                for(int i = 0 ;i < choiceId.length ;i++ ){
                    question = studentMapper.QuestionList(Integer.parseInt(choiceId[i]));
                    questionList.add(question);
                }
                SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String  endTime=sim.format(studentMapper.selectEndTime(examid));
                return new ResultQuestion(studentMapper.getTitle(examid),
                        studentMapper.select_operation(examid),questionList, meta,endTime,studentMapper.getExamTotalScore(examid));
            }
            meta.setMessage("禁止访问");
            meta.setStatus(403);
            return new ResultQuestion(null,null,null,meta,null,null);
        }
         if(role.equals("2")){
            meta.setMessage("查询成功");
            meta.setStatus(200);
            String[] choiceId = studentMapper.selectQuestionId(examid).split("-");
            question question =  new question() ;
            List<question> questionList = new ArrayList<>();
            for(int i = 0 ;i < choiceId.length ;i++ ){
                question = studentMapper.QuestionList(Integer.parseInt(choiceId[i]));
                questionList.add(question);
            }
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String  endTime=sim.format(studentMapper.selectEndTime(examid));
            return new ResultQuestion(studentMapper.getTitle(examid),
                    studentMapper.select_operation(examid),questionList, meta,endTime,studentMapper.getExamTotalScore(examid));
        }
        meta.setMessage("无法显示");
        meta.setStatus(400);
        return new ResultQuestion(null,null,null,meta,null,null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "user/gethomephoto")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultPhoto getHomePhoto(HttpServletRequest request ) {

        Meta meta = new Meta();
        meta.setStatus(200);
        meta.setMessage("获取成功");
        return new ResultPhoto(studentMapper.getHomePhoto(),meta);
    }
    @RequestMapping(method = RequestMethod.GET, value = "user/getrole")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultRole getRole(HttpServletRequest request ) {
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id = jwt.getClaim("id").asString();
        String role = jwt.getClaim("role").asString();
        Meta meta = new Meta();
        if(role.equals("1")){
            if(studentMapper.SelectToken(id).equals(token)){
                meta.setStatus(200);
                meta.setMessage("获取成功");
                return new ResultRole(role,meta);
            }
        }
        if(role.equals("2")){
            if(teacherMapper.SelectTeacherToken(id).equals(token)){
                meta.setStatus(200);
                meta.setMessage("获取成功");
                return new ResultRole(role,meta);
            }
        }

        meta.setStatus(400);
        meta.setMessage("获取失败");
        return new  ResultRole(null,meta);
    }


        /*
          生成左边栏，传入role就可以获得
        * role从token中解出来
        * */
    public List<firstMenu> getMenuList(String role){
        String[] roles = studentMapper.selectRole(role).split("-");
        List<firstMenu> firstMenuList = new ArrayList<>();
        for ( int i = 0 ; i < roles.length ; i++){
            String[] firstLevelRole = studentMapper.selectFirstRole(roles[i]).split("-");
            List<secondMenu> secendMenuList =  new ArrayList<>();
            secondMenu secondMenu = new secondMenu() ;
            firstMenu firstMenu =  new firstMenu();
            for( int j = 0 ; j < firstLevelRole.length; j++){
                secondMenu =  studentMapper.selectSecondMenu(firstLevelRole[j]);
                secendMenuList.add(secondMenu);
            }
            firstMenu= studentMapper.getFirstLevel(roles[i]);
            firstMenu.setChildren(secendMenuList);
            firstMenuList.add(firstMenu);
        }
        return firstMenuList;
    }
    @GetMapping(value = "user/gettrain")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultTrain getTrain(HttpServletRequest request,@RequestParam("id") String id  ) throws ParseException {
        String token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String role = jwt.getClaim("role").asString();
        Meta meta = new Meta();
        if(role!=null) {
            System.out.println(id);
            String choice = studentMapper.selectChoiceInfo(Integer.parseInt(id));
            String[] choiceArrays = choice.split("-");
            question question = new question();
            List<question> questionList = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化输出日期
            Date afterDate = sdf.parse(studentMapper.getTrainstarttime(Integer.parseInt(id)));
            for (int i = 0; i < choiceArrays.length; i=i+2) {
                question = studentMapper.QuestionList(Integer.parseInt(choiceArrays[i].trim()));
                question.setQuestionstarttime(afterDate);
                Date now = new Date();
                long time = (long) (Double.parseDouble(choiceArrays[i+1].trim())*60*1000);//60秒
                //得到开始时间
                afterDate = new Date(afterDate.getTime()+ time);
                question.setQuestionendtime(afterDate);
                questionList.add(question);
            }
            meta.setStatus(200);
            meta.setMessage("获取成功");
            return new ResultTrain(studentMapper.getTrainName(Integer.parseInt(id)),studentMapper.getTrainstarttime(Integer.parseInt(id)),studentMapper.getTrainscore(Integer.parseInt(id)),questionList,meta);
        }
        meta.setStatus(400);
        meta.setMessage("获取失败");
        return new ResultTrain(null,null,null,null,meta);
    }
}
