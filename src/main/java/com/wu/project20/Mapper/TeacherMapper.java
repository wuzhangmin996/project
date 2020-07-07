package com.wu.project20.Mapper;

import com.wu.project20.bean.Exam;
import com.wu.project20.bean.Teacher;
import com.wu.project20.bean.choiceRecord;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface TeacherMapper {

    @Select("select * from teacher where tid=#{id}")//查询老师的信息
    public Teacher SelectTeacher(String id);

    @Update("Update teacher set password =#{password} where tid = #{id}")//修改密码
    public void updateTeacher(String id, String password);

    @Insert("insert into teacher values(#{工号},#{姓名},#{性别},#{主讲课程},#{进校时间},#{密码})")//插入一条信息
    public void insertTeacher(Teacher teacher);
    @Delete("delete from teacher where tid = #{id}")//删除一条老师信息
    public void deleteTeacher(String id );


    @Select("select top (select #{pageSize}) * from (select row_number() over(order by [tid])" +
            " as rownumber , * from [teacher])" +
            " temp_row where rownumber>(#{pageIndex}-1)*#{pageSize}")//分页查看
    public List<Teacher> select_page_teacher(int pageIndex, int pageSize);



    @Select("select count(*) from teacher where tid = #{username}")
    int teacherExist(String username);

    @Select("select password from teacher where tid = #{username}")
    String getPassword(String username);

    @Update("update teacher set token = #{token} where  tid= #{username}")
    void CreateToken(String token, String username);

    @Select("select token from teacher where tid = #{id}")
    public String SelectTeacherToken(String id );

    @Select("select top (select #{pageSize}) * from (select row_number() over(order by [choicequestionid])" +
            " as rownumber , * from [choiceinfo] where description like #{choicename})" +
            " temp_row where rownumber>(#{pageIndex}-1)*#{pageSize}")
    List<choiceRecord> questionPageList(String choicename,int pageIndex, int pageSize);

    @Select("select count(*) from choiceinfo where description like #{choice}")
    int selectTotalPage(String choice );

    @Select("select top 1 choicequestionid from choiceinfo order by choicequestionid desc")
    int selectBeforeChoiceQuestionId();

    @Select("select * from examinfo,teacher  where examid = #{examid} and teacher.tid = examinfo.tid")
    List<Exam> getExamInfo(String examid);

    @Update("update examinfo set examname = #{examname} , examendtime = #{examendtime}" +
            " , examstarttime = #{examstarttime} where examid = #{examid}")
    void updateExamInfo(Exam exam );

    @Insert("insert into choiceinfo values(#{choicequestionid},#{description},#{choicea},#{choiceb},#{choicec},#{choiced},#{answer},#{score})")
    void InsertChoice(choiceRecord question);

    @Select("select top 1 examid from examinfo order by examid desc ")
    int getLastExamid();

    @Insert("insert into examinfo values(#{examid},#{examname},#{examendtime},#{tid},#{examstarttime},#{examcreatetime},#{choicequestionid},#{examscore})")
    void createExam(Exam exam);

    @Select("select * from choiceinfo where choicequestionid = #{id}")
    choiceRecord getChoiceInfo(String id);

    @Update("update choiceinfo set description = #{description},choicea= #{choicea},choiceb = #{choiceb}" +
            ",choicec = #{choicec} ,choiced=#{choiced} ,answer =#{answer} ,score = #{score}  where choicequestionid =#{choicequestionid}")
    void updateChoiceInfo(choiceRecord choiceRecord);

    @Delete("delete from examinfo where examid = #{examid}")
    void deleteExam(int examid);

    @Select("select count(*) from choicerecord where examid = #{examid}")
    int  getchoiceNum(String examid);

    @Delete("delete from operation where examid = #{examid}")
    void deleteOperation(int examid);

    @Delete("delete from choicerecord where examid  = #{examid}")
    void deleteChoiceInfo(int examid);

    @Delete("delete from choicerecord where examid = #{examid}")
    void deleteScore(int examid);

    @Select("select top 1 trainid from train order by trainid desc ")
    int getLastTrain();

    @Insert("insert into train values(#{trainid},#{trainname},#{trainstarttime},#{tid},#{traincreatetime},#{choicequestionid},#{score})")
    void addTrain(int trainid, String trainname, String trainstarttime,String tid,Date traincreatetime, String choicequestionid,  String score);
}
