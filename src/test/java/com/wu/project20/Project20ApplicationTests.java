package com.wu.project20;

import com.wu.project20.Mapper.ManagerMapper;
import com.wu.project20.Mapper.ScoreDao;
import com.wu.project20.Mapper.StudentMapper;
import com.wu.project20.Mapper.TeacherMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Project20ApplicationTests {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    ScoreDao scoreDao;

    @Autowired
    ManagerMapper managerMapper;



    @Test
    void contextLoads() throws Exception {
        /*System.out.println(scoreDao.SelectScoreById("2007021536"));

        System.out.println(managerMapper.selectManager("2017051150"));

        studentMapper.updatestudent("123456","2007021536");*/
       /* String name = "张";
        System.out.println(studentMapper.select_name_student("%"+name+"%",2,3));
       */ /*
       System.out.println(studentMapper.select_page_student(1,10));*/
       //System.out.println(tokenProccessor.makeToken());
        System.out.println(studentMapper.SelectID("2007021536"));
      /* String token =  new TokenTools().createToken("2017051150","123456");
       System.out.println(token);
        System.out.println();
        TokenTools tokenTools = new TokenTools();
        DecodedJWT jwt = tokenTools.decodeToken(token);
        System.out.println(jwt.getClaim("id").asString());
        System.out.println(jwt.getClaim("password").asString());
        System.out.println(jwt.getClaim("date").asDate());*/
      /* System.out.println(studentMapper.SelectToken("2007021536").getToken());*/


    }

}
