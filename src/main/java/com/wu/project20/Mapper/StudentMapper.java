package com.wu.project20.Mapper;

import com.wu.project20.bean.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("select * from 学生,专业,班 where 学生.profession = 专业.profession and 班.classnumber =学生.class and 学生.profession = 班.profession and SID=#{id} ")//查询学生信息
    public Student SelectID(String id);

    @Delete("delete from 学生 where 学号 = #{id}")//删除一条学生信息
    public void  deletestudent(String id);

    @Insert("insert into 学生  values(#{学号},#{姓名},#{性别},#{专业},#{年级},#{班序号},#{进校时间},#{密码})")//插入一条学生信息
    public  void insertstudent(Student student);

    @Update("update 学生 set 密码 = #{password} where SID = #{id}")//修改密码
    public  void  updatestudent(String password, String id);

    @Select("select top (select #{pageSize}) * from (select row_number() over(order by [SID])" +
            " as rownumber , * from [学生] where name like #{name})" +
            " temp_row where rownumber>(#{pageIndex}-1)*#{pageSize}")//相似查找
    public List<Student> select_name_student(String name,int pageIndex,int pageSize);

    @Select("select top (select #{pageSize}) * from (select row_number() over(order by [SID])" +
            " as rownumber , * from [学生],[专业] where 学生.profession  = 专业.profession)" +
            " temp_row where rownumber>(#{pageIndex}-1)*#{pageSize}")//分页查询
    public List<Student> select_page_student(int pageIndex,int pageSize);

    @Update("update 学生 set token = #{token} where  SID= #{id}")
    public void CreateToken(String token,String id );

    @Select("select token from 学生 where SID = #{id}")
    public Student SelectToken(String id );
}
