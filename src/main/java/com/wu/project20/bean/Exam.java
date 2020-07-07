package com.wu.project20.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Exam {
    private int examid ;
    private String examname  ;
    private String  examendtime ;
    private String name ;
    private String tid ;
    private String examstarttime ;
    private String  examcreatetime ;
    private String choicequestionid;
    private String examscore;

    public String getExamscore() {
        return examscore;
    }

    public void setExamscore(String examscore) {
        this.examscore = examscore;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getChoicequestionid() {
        return choicequestionid;
    }

    public void setChoicequestionid(String choicequestionid) {
        this.choicequestionid = choicequestionid;
    }

    public int getExamid() {
        return examid;
    }

    public void setExamid(int examid) {
        this.examid = examid;
    }

    public String getExamname() {
        return examname;
    }

    public void setExamname(String examname) {

        this.examname = examname;
    }

    public String  getExamendtime() {
        return examendtime;
    }

    public void setExamendtime(Date examendtime) {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  time=sim.format(examendtime);
        this.examendtime = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getExamstarttime() {
        return examstarttime;
    }

    public void setExamstarttime(Date examstarttime) {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  time=sim.format(examstarttime);
        this.examstarttime = time;
    }

    public String  getExamcreatetime() {
        return examcreatetime;
    }

    public void setExamcreatetime(Date examcreatetime) {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  time=sim.format(examcreatetime);
        this.examcreatetime = time;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examid=" + examid +
                ", examname='" + examname + '\'' +
                ", examendtime='" + examendtime + '\'' +
                ", name='" + name + '\'' +
                ", examstarttime='" + examstarttime + '\'' +
                ", examcreatetime='" + examcreatetime + '\'' +
                '}';
    }
}
