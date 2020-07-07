package com.wu.project20.bean;

import java.util.Date;

public class Htmldata {
    private String examname;
    private  Date[] examtime ;
    private String selectid;
    private String pagenum ;
    private String choicename;
    private int examid;
    private int examscore;

    public int getExamscore() {
        return examscore;
    }

    public void setExamscore(int examscore) {
        this.examscore = examscore;
    }

    public String getPagenum() {
        return pagenum;
    }

    public void setPagenum(String pagenum) {
        this.pagenum = pagenum;
    }

    public int getExamid() {
        return examid;
    }

    public void setExamid(int examid) {
        this.examid = examid;
    }

    public String getChoicename() {
        return choicename;
    }

    public void setChoicename(String choicename) {
        this.choicename = choicename;
    }

    public Date[] getExamtime() {
        return examtime;
    }

    public void setExamtime(Date[] examtime) {
        this.examtime = examtime;
    }


    public String getExamname() {
        return examname;
    }

    public void setExamname(String examname) {
        this.examname = examname;
    }


    public String getSelectid() {
        return selectid;
    }

    public void setSelectid(String selectid) {
        this.selectid = selectid;
    }
}
