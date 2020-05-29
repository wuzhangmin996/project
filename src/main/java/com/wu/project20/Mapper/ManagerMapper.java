package com.wu.project20.Mapper;

import com.wu.project20.bean.Manager;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ManagerMapper {
    @Select("select * from 管理员 where MID=#{id}")//用id查询管理员信息
    public Manager selectManager(String id);

    @Update("update 管理员 set 密码 = #{password} where MID = #{id}")//修改密码
    public void updateManager(String id,String password );
}
