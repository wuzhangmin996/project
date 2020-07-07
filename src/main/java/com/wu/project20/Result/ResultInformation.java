package com.wu.project20.Result;

import com.wu.project20.bean.Manager;
import com.wu.project20.bean.Meta;
import com.wu.project20.bean.Student;
import com.wu.project20.bean.Teacher;

public class ResultInformation {
    private Student student ;
    private Teacher teacher ;
    private Manager manager ;
    private Meta meta ;

    public ResultInformation(Student student, Teacher teacher, Manager manager, Meta meta) {
        this.student = student;
        this.teacher = teacher;
        this.manager = manager;
        this.meta = meta;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }



    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
