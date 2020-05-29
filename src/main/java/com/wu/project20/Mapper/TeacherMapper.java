package com.wu.project20.Mapper;

import com.wu.project20.bean.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {

    @Select("select * from 老师 where 工号=#{id}")//查询老师的信息
    public Teacher SelectTeacher(String id);

    @Update("Update 老师 set 密码 =#{password} where id = #{id}")//修改密码
    public void updateTeacher(String id, String password);

    @Insert("insert into 老师 values(#{工号},#{姓名},#{性别},#{主讲课程},#{进校时间},#{密码})")//插入一条信息
    public void insertTeacher(Teacher teacher);
    @Delete("delete from 老师 where id = #{id}")//删除一条老师信息
    public void deleteTeacher(String id );

    @Select("select * from 老师 where 姓名 like ")
    public Teacher teacherinfolike(String name);

    @Select("select top (select #{pageSize}) * from (select row_number() over(order by [工号])" +
            " as rownumber , * from [老师])" +
            " temp_row where rownumber>(#{pageIndex}-1)*#{pageSize}")//分页查看
    public List<Teacher> select_page_teacher(int pageIndex, int pageSize);

    @Select("select * from 老师 where 姓名 like #{name}")
    public List<Teacher> select_name_teacher(String name);
}
