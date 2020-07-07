package com.wu.project20.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    private String sid;
    private String  name;
    private String gender;
    private String majorname;
    private int grade;
    private int  classindex;
    private String  schooltime;
    private int system;
    private String majorcatagory;
    private String academicdepartment;
    private String location;
    private String classname;
    private String majorquality ;
    private String majorlevel;
    private String nation ;
    private String nativeplace ;
    private String politicbackground;

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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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

    public String getMajorname() {
        return majorname;
    }

    public void setMajorname(String majorname) {
        this.majorname = majorname;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClassindex() {
        return classindex;
    }

    public void setClassindex(int classindex) {
        this.classindex = classindex;
    }

    public String getSchooltime() {
        return schooltime;
    }

    public void setSchooltime(Date schooltime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String timestart = formatter.format(schooltime);
        this.schooltime = timestart;
    }

    public int getSystem() {
        return system;
    }

    public void setSystem(int system) {
        this.system = system;
    }

    public String getMajorcatagory() {
        return majorcatagory;
    }

    public void setMajorcatagory(String majorcatagory) {
        this.majorcatagory = majorcatagory;
    }

    public String getAcademicdepartment() {
        return academicdepartment;
    }

    public void setAcademicdepartment(String academicdepartment) {
        this.academicdepartment = academicdepartment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getMajorquality() {
        return majorquality;
    }

    public void setMajorquality(String majorquality) {
        this.majorquality = majorquality;
    }

    public String getMajorlevel() {
        return majorlevel;
    }

    public void setMajorlevel(String majorlevel) {
        this.majorlevel = majorlevel;
    }


}
