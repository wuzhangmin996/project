package com.wu.project20.Mapper;

import com.wu.project20.bean.Manager;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ManagerMapper {
    @Select("select * from manager where MID=#{id}")//用id查询管理员信息
    public Manager selectManager(String id);

    @Update("update manager set password = #{password} where MID = #{id}")//修改密码
    public void updateManager(String id,String password );

    @Select("select count(*) from manager where mid = #{username}")
    int managerExist(String username);

    @Select(("select password from manager where  mid =#{username}"))
    String getPassword(String username);

    @Update("update manager set token = #{token} where  mid= #{username}")
    void CreateToken(String token, String username);


    String SelectManagerToken(String id);
}
