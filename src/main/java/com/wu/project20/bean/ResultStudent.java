package com.wu.project20.bean;

public class ResultStudent {
    private Student student;
    private Meta meta;

    public ResultStudent(Student student, Meta meta) {
        super();
        this.student = student ;
        this.meta = meta ;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
