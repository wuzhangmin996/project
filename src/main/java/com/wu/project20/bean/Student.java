package com.wu.project20.bean;

import java.util.Date;

public class Student {
    private String SID;
    private String  name;
    private String Ssex;
    private String professionname;
    private int grade;
    private int  classnumber;
    private Date schooltime;
    private String password;
    private int system;
    private String profesdioncatagory;
    private String academicdepartment;
    private String location;
    private String classname;
    private String professionquality ;
    private String level;
    private String token ;

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }

    public String getProfessionname() {
        return professionname;
    }

    public void setProfessionname(String professionname) {
        this.professionname = professionname;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getSchooltime() {
        return schooltime;
    }

    public void setSchooltime(Date schooltime) {
        this.schooltime = schooltime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClassnumber() {
        return classnumber;
    }

    public void setClassnumber(int classnumber) {
        this.classnumber = classnumber;
    }

    public int getSystem() {
        return system;
    }

    public void setSystem(int system) {
        this.system = system;
    }

    public String getProfesdioncatagory() {
        return profesdioncatagory;
    }

    public void setProfesdioncatagory(String profesdioncatagory) {
        this.profesdioncatagory = profesdioncatagory;
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

    public String getProfessionquality() {
        return professionquality;
    }

    public void setProfessionquality(String professionquality) {
        this.professionquality = professionquality;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Student{" +
                "SID='" + SID + '\'' +
                ", name='" + name + '\'' +
                ", Ssex='" + Ssex + '\'' +
                ", professionname='" + professionname + '\'' +
                ", grade=" + grade +
                ", classnumber=" + classnumber +
                ", schooltime=" + schooltime +
                ", password='" + password + '\'' +
                ", system=" + system +
                ", profesdioncatagory='" + profesdioncatagory + '\'' +
                ", academicdepartment='" + academicdepartment + '\'' +
                ", location='" + location + '\'' +
                ", classname='" + classname + '\'' +
                ", professionquality='" + professionquality + '\'' +
                ", level='" + level + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
