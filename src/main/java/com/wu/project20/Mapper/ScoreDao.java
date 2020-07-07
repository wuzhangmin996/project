package com.wu.project20.Mapper;

import com.wu.project20.bean.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ScoreDao {
    /*
     * 执行存储过程，并且执行的结果返回到表
     * */
    @Select("{call 阅卷_操作题_数据库分析(#{id})}")
    public void callback(Integer id);
    /*
     * 查询出成绩
     * */
    @Select("select * from 自动阅卷_DB_操作 where ID=#{id}")
    public Score SelectScoreById(String id);

    @Select(" select count(*) from 自动阅卷_DB_操作 where ID = #{id}")
    int existScore(String id);


}
