package com.wu.project20.bean;

import java.util.Date;

public class Teacher {
    private String 工号;
    private String 姓名;
    private String 性别;
    private String 主讲课程;
    private Date 进校时间;
    private String 密码;
    private String  token;

    public String get工号() {
        return 工号;
    }

    public void set工号(String 工号) {
        this.工号 = 工号;
    }

    public String get姓名() {
        return 姓名;
    }

    public void set姓名(String 姓名) {
        this.姓名 = 姓名;
    }

    public String get性别() {
        return 性别;
    }

    public void set性别(String 性别) {
        this.性别 = 性别;
    }

    public String get主讲课程() {
        return 主讲课程;
    }

    public void set主讲课程(String 主讲课程) {
        this.主讲课程 = 主讲课程;
    }

    public Date get进校时间() {
        return 进校时间;
    }

    public void set进校时间(Date 进校时间) {
        this.进校时间 = 进校时间;
    }

    public String get密码() {
        return 密码;
    }

    public void set密码(String 密码) {
        this.密码 = 密码;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "工号='" + 工号 + '\'' +
                ", 姓名='" + 姓名 + '\'' +
                ", 性别='" + 性别 + '\'' +
                ", 主讲课程='" + 主讲课程 + '\'' +
                ", 进校时间=" + 进校时间 +
                ", 密码='" + 密码 + '\'' +
                ", token=" + token +
                '}';
    }
}
