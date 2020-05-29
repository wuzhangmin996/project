package com.wu.project20.bean;

import java.util.Date;

public class Manager {
    private String MID ;
    private String 姓名;
    private String 性别;
    private Date 进校时间;
    private String 密码;

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
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

    @Override
    public String toString() {
        return "Maneger{" +
                "MID='" + MID + '\'' +
                ", 姓名='" + 姓名 + '\'' +
                ", 性别='" + 性别 + '\'' +
                ", 进校时间=" + 进校时间 +
                ", 密码='" + 密码 + '\'' +
                '}';
    }
}
