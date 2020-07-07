package com.wu.project20.Mapper;

import com.wu.project20.bean.*;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface StudentMapper {


    @Select("select * from student,majorinfo,classinfo where student.major = majorinfo.major and classinfo.classindex =student.classindex and student.major = classinfo.major and sid=#{id} ")//查询学生信息
    public Student SelectID(String id);

    @Delete("delete from student where sid = #{id}")//删除一条学生信息
    public void  deletestudent(String id);

    @Insert("insert into student  values(#{学号},#{姓名},#{性别},#{专业},#{年级},#{班序号},#{进校时间},#{密码})")//插入一条学生信息
    public  void insertstudent(Student student);

    @Update("update student set password = #{password} where sid = #{id}")//修改密码
    public  void  updatestudent(String password, String id);

    @Select("select top (select #{pageSize}) * from (select row_number() over(order by [sid])" +
            " as rownumber , * from [student] where name like #{name})" +
            " temp_row where rownumber>(#{pageIndex}-1)*#{pageSize}")//相似查找
    public List<Student> select_name_student(String name,int pageIndex,int pageSize);

    @Select("select top (select #{pageSize}) * from (select row_number() over(order by [sid])" +
            " as rownumber , * from [student],[majorinfo] where student.major  = majorinfo.major)" +
            " temp_row where rownumber>(#{pageIndex}-1)*#{pageSize}")//分页查询
    public List<Student> select_page_student(int pageIndex,int pageSize);

    @Update("update student set token = #{token} where  sid= #{id}")
    public void CreateToken(String token,String id );

    @Select("select token from student where sid = #{id}")
    public String SelectToken(String id );

    @Select("select * from operation where examid =#{id}")
    public List<operation>  select_operation(int id );

    @Select("select examname from examinfo where examid = #{id}")
    public String getTitle(int id);


    @Select("select * from choiceinfo where choicequestionid = #{id}")
    question QuestionList(int id);

    @Select("select answer from choiceinfo where choicequestionid = #{id}")
    String selectanswer(int id );

    @Select("select score from choiceinfo where choicequestionid = #{id}")
    String selectscore(int id );

    @Select("Select distinct score from choicerecord where examid = #{examid} and sid = #{stuid}")
    String  selectChoiceScore(String examid, String stuid);

    @Insert("insert into choicerecord values(#{examid},#{id},#{score})")
    void insertChoiceScore(int examid, String id,double score);

    @Select("select examendtime from examinfo where examid  = #{examid}")
    Date selectEndTime(int examid);

    @Select("select * from choiceinfo  where  choicequestionid = #{questionid}")
    public choiceRecord getChoiceRecord(int questionid );


   @Insert("insert into choicerecord values(#{choice},#{examid},#{id},#{date})")
    void insertChoiceRecord(int examid, String id, String choice,Date date);

   @Select("select  choice from choicerecord where sid = #{id} and examid =#{examid}")
    String  getChioce(String id, int examid);

   @Select("select count(*) from choicerecord where examid = #{examid} and sid = #{id}")
    int  selectrecordchoice(int examid ,String id );

   @Select("select * from choicerecord ,examinfo where choicerecord.examid = examinfo.examid " +
                  "and choicerecord.examid = #{examid} and sid = #{id} ")
    Time getTime(int examid, String id);

    @Select("select count(*) from student where sid = #{username}")
      int studentExist( String  username );

    @Select(("select password from student where  sid =#{username}"))
    String getPassword(String username );

    @Select("select firstlevel from role where roleid = #{roleid}")
    String selectRole(String roleid );

    @Select("select secendlevel from firstlevel where fid =  #{fid}")
    String selectFirstRole(String  fid);

    @Select("select * from secondlevel where secondid =  #{secondid}")
    secondMenu selectSecondMenu(String secendid);

    @Select("select * from firstlevel where fid = #{role} ")
    firstMenu getFirstLevel(String role);

    @Select("select top (select #{pageSize}) * from (select row_number() over(order by [examid])" +
            " as rownumber ,examid,examname,examendtime,examstarttime,name,examcreatetime,examscore from [examinfo] ,[teacher] where examinfo.tid = teacher.tid  and examname like #{examname})" +
            " temp_row where rownumber>(#{pageIndex}-1)*#{pageSize}")
    List<Exam> getExamListByLike(String examname,int pageIndex,int pageSize);

    @Select("select choicequestionid  from examinfo where examid = #{examid} ")
    String selectQuestionId(int examid);

    @Select("select examstarttime from examinfo where examid  = #{examid}")
    Date selectStartTime(int examid);

    @Select("select count(*) from examinfo where examname like #{choice}")
    int totalExam(String choice);

    @Select(" select * from homephoto  ")
    List<homephoto> getHomePhoto();



    @Select(("select student.sid, name ,classname from classinfo , student, choicerecord " +
            " where classinfo.major = student.major and student.classindex= classinfo.classindex and  student.sid = choicerecord.sid and examid =#{examid} and choicerecord.sid=#{sid} "))
    StudentAnalysis getStudentScoreInfo(int  examid,String sid );

    @Select("select sid from choicerecord where examid = #{examid}")
    String[] selectidfromchoicescore(String examid);

    @Select("select examscore from examinfo where examid = #{examid}")
    String getExamTotalScore(int examid);

    @Select("select choicequestionid from train where trainid=#{id}")
    String selectChoiceInfo(Integer id);

    @Select("select trainstarttime  from train where trainid =#{id}")
    String getTrainstarttime(Integer id);

    @Select("select trainname from train where trainid =#{id}")
    String getTrainName(Integer id);

    @Select("select trainscore from train where trainid = #{id}")
    String getTrainscore(Integer id);
}
