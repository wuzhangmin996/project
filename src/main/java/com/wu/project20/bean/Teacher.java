package com.wu.project20.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Teacher {
    private String tid;
    private String name ;
    private String gender ;
    private String lesson ;
    private String schooltime ;
    private String educationalbackgroud;
    private String graduatedschool;
    private String nation ;
    private String worktype;
    private String jointime;//加入工作时间
    private String onduty;
    private String nativeplace ;
    private String politicbackground;
    private String academicdepartment;
    private String title ;
    private String inorganization;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInorganization() {
        return inorganization;
    }

    public void setInorganization(String inorganization) {
        this.inorganization = inorganization;
    }

    public String getAcademicdepartment() {
        return academicdepartment;
    }

    public void setAcademicdepartment(String academicdepartment) {
        this.academicdepartment = academicdepartment;
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public String getJointime() {

        return jointime;
    }

    public void setJointime(Date jointime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String time= formatter.format(jointime);
        this.jointime = time;
    }

    public String getOnduty() {
        return onduty;
    }

    public void setOnduty(String onduty) {
        this.onduty = onduty;
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    public String getPoliticbackground() {
        return politicbackground;
    }

    public void setPoliticbackground(String politicbackground) {
        this.politicbackground = politicbackground;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getSchooltime() {
        return schooltime;
    }

    public void setSchooltime(Date schooltime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String timestart = formatter.format(schooltime);
        this.schooltime = timestart;
    }

    public String getEducationalbackgroud() {
        return educationalbackgroud;
    }

    public void setEducationalbackgroud(String educationalbackgroud) {
        this.educationalbackgroud = educationalbackgroud;
    }

    public String getGraduatedschool() {
        return graduatedschool;
    }

    public void setGraduatedschool(String graduatedschool) {
        this.graduatedschool = graduatedschool;
    }
}
