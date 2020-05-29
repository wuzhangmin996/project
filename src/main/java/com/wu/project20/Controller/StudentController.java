package com.wu.project20.Controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.wu.project20.Mapper.ScoreDao;
import com.wu.project20.Mapper.StudentMapper;
import com.wu.project20.Token.TokenTools;
import com.wu.project20.bean.*;
import com.wu.project20.unZip.UnZip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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



    /*
    * 学生登录，username为学号，password为密码，生成token，
    * 用学号+密码+时间生成token。存进数据库里面，成为验证的一项重大工具
    * */
    @RequestMapping(method = RequestMethod.POST , value = "student/login")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultStudent studentLogin(HttpServletRequest request,
                                      @RequestBody StudentHtml studentHtml){
        /*String toke = request.getHeader("token");
        System.out.println(toke);*/
        String username =  studentHtml.getUsername();
        String password =  studentHtml.getPassword();/*
        String username =  request.getParameter("username");
        String password =  request.getParameter("password");*/
        Student student =  studentMapper.SelectID(username);
        Meta meta = new Meta();
        if(password.trim().equals(student.getPassword().trim())){
            TokenTools tokenTools = new TokenTools() ;
            String token = tokenTools.createToken(username,password);
            studentMapper.CreateToken(token,username);//将token传到数据库，进行保存。
            student.setToken(token);
            meta.setMessage("登录成功");
            meta.setStatus(200);
            return new ResultStudent(student,meta);
        }
        meta.setMessage("登录失败");
        meta.setStatus(404);
        return new ResultStudent(null ,meta);
    }
    /*
    * 通过点击个人信息一栏，通过传入的token，进行解码，解出id，
    * 查询出id所对应的token，看是否相等，等，则将个人信息进行返回。*/
    @RequestMapping(method = RequestMethod.POST ,value = "student/SingleInformation")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultStudent studentLogin(HttpServletRequest request){
        TokenTools tokenTools = new TokenTools() ;
        String  token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id  =  jwt.getClaim("id").asString();
        Meta meta = new Meta();
        if(token.trim().equals(studentMapper.SelectToken(id).getToken())){
            meta.setMessage("获取成功");
            meta.setStatus(200);
            return new ResultStudent(studentMapper.SelectID(id),meta);
        }
        meta.setMessage("获取失败");
        meta.setStatus(404);
        return new ResultStudent(null,meta);
    }
    @RequestMapping(method =  RequestMethod.POST ,value = "student/upload")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultStudent fileUpLoad(@RequestParam("file") MultipartFile file, Map<String, Object> map,
                                    HttpServletRequest request) throws Exception {
        TokenTools tokenTools = new TokenTools() ;
        String  token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id  =  jwt.getClaim("id").asString();
        Meta meta = new Meta();
        if(token.trim().equals(studentMapper.SelectToken(id).getToken())){
            String fileName=file.getOriginalFilename();
            //String fileNameSuffix=fileName.substring(fileName.lastIndexOf(".") + 1);
            //long fileSize=file.getSize();//字节数
            String filePath="C:\\Users\\ASUS\\Desktop\\wer\\";
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

            scoreDao.callback(Integer.valueOf(id));
            meta.setMessage("评判成功");
            meta.setStatus(200);
            return new ResultStudent(null,meta);
        }
        meta.setStatus(404);
        meta.setMessage("评判失败");
        return new ResultStudent(null,meta);
    }
    /*
     * 修改密码
     * 通过进行对token的验证，利用反解出的id，进行对密码的修改*/
    @RequestMapping(method = RequestMethod.POST , value = "student/update")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultStudent updateStudent(HttpServletRequest request){
        TokenTools tokenTools = new TokenTools() ;
        String  token = request.getHeader("Authorization");
        System.out.println(token);
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id  =  jwt.getClaim("id").asString();
        Meta meta = new Meta();
        if(token.trim().equals(studentMapper.SelectToken(id).getToken())){
            String password =  request.getParameter("password");
            /*String password =  studentHtml.getPassword*/
            studentMapper.updatestudent(password,id);
            meta.setMessage("修改成功");
            meta.setStatus(200);
            return new ResultStudent(null,meta);
        }
        meta.setMessage("修改失败");
        meta.setStatus(404);
        return new ResultStudent(null,meta);
    }

    /*
    * 通过id，查询成绩*/
    @RequestMapping(method = RequestMethod.POST,value = "student/score")
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultScore score(HttpServletRequest request)  {
        TokenTools tokenTools = new TokenTools() ;
        String  token = request.getHeader("Authorization");
        DecodedJWT jwt = tokenTools.decodeToken(token);
        String id  =  jwt.getClaim("id").asString();
        Meta meta = new Meta();
        if(token.trim().equals(studentMapper.SelectToken(id).getToken())){
            meta.setMessage("查询成功");
            meta.setStatus(200);
            return new ResultScore(scoreDao.SelectScoreById(id),meta);
        }
        meta.setMessage("查询失败");
        meta.setStatus(404);
        return new ResultScore(null,meta);
    }


    /*
   删除一条学生记录,id为学生的学号
   */
    @RequestMapping(method = RequestMethod.POST,value = "delete/student")
    @ResponseBody
    public ResultStudent deletestudent(HttpServletRequest request){
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
    @RequestMapping(method = RequestMethod.POST,value = "student/page")
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
