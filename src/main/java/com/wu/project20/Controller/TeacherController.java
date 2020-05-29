package com.wu.project20.Controller;

import com.wu.project20.Mapper.TeacherMapper;
import com.wu.project20.Token.TokenTools;
import com.wu.project20.bean.ResultTeacher;
import com.wu.project20.bean.Teacher;
import com.wu.project20.bean.TeacherHtml;
import com.wu.project20.bean.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    TeacherMapper teacherMapper;

    @RequestMapping(method = RequestMethod.POST , value = "teacher/login")//老师登录,得到所有的数据
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @ResponseBody
    public ResultTeacher TeacherLogin(HttpServletRequest request,
                                      @RequestBody TeacherHtml teacherHtml){
        System.out.println(teacherHtml.getUsername());
        Teacher teacher = teacherMapper.SelectTeacher(teacherHtml.getUsername());

        teacher.setToken(new TokenTools().createToken(teacherHtml.getUsername(),teacherHtml.getPassword()));
        Meta meta = new Meta();
        //if(password.equals("123456")){
            meta.setMessage("登录成功");
            meta.setStatus(200);
            return new ResultTeacher(teacher, meta);
       // }
        //meta.setMessage("登录失败");
       // meta.setStatus(213);
       // return new Result(null,meta);
    }

    @GetMapping("teacher/update/{id}&&{password}")//老师修改密码
    public void UpdateTeacher(@PathVariable("id") String id ,@PathVariable("password") String password){
        teacherMapper.updateTeacher(id,password);
    }

    @RequestMapping("teacher/insert")//插入一条老师信息
    public void InsertTeacher(Teacher teacher){
        teacherMapper.insertTeacher(teacher);
    }

    @PostMapping("teacher/delete/{id}")//删除一条老师信息
    public void DeleteTeacher(@RequestParam("id") String id){
        teacherMapper.deleteTeacher(id);
    }

    @GetMapping("teacher/search/{name}")//相似查询老师信息
    public Teacher TeacherInfoLike(@PathVariable("name") String name){
        return teacherMapper.teacherinfolike(name);
    }

    @PostMapping("teacher/page")
    public List<Teacher> Teacher_page(@RequestParam("page") int page,
                                      @RequestParam("pageSize") int pageSize){
        return teacherMapper.select_page_teacher(page,pageSize);
    }
}
