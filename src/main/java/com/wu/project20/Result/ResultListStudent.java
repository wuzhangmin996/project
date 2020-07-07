package com.wu.project20.Result;

import com.wu.project20.bean.Meta;
import com.wu.project20.bean.Student;

import java.util.List;

public class ResultListStudent {
    private List<Student> student;
    private Meta meta ;

    public ResultListStudent(List<Student> student, Meta meta) {
        super();
        this.student = student;
        this.meta = meta;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
