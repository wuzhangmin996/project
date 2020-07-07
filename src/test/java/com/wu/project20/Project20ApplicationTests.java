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
      /*  String choice ="1-1.5 - 2-2.5";
        String[] choiceArrays = choice.split("-");
        question question = new question();
        List<question> questionList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化输出日期
        Date afterDate = sdf.parse("2020-06-27 00:00:00.000");
        for (int i = 0; i < choiceArrays.length; i=i+2) {
            question = studentMapper.QuestionList(Integer.parseInt(choiceArrays[i].trim()));
            Date now = new Date();
            long time = (long) (Double.parseDouble(choiceArrays[i+1].trim())*60*1000);//60秒
            //得到开始时间
            afterDate = new Date(afterDate.getTime()+ time);

            question.setTraintime(afterDate);
            System.out.println(question.toString());
            questionList.add(question);

        }*/
    }

}
