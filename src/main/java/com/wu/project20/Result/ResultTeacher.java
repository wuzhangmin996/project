package com.wu.project20.Result;

import com.wu.project20.bean.Meta;
import com.wu.project20.bean.Teacher;

public class ResultTeacher {
    private Teacher teacher;
    private Meta meta;
    public ResultTeacher(Teacher teacher, Meta meta){
        super();
        this.teacher = teacher ;
        this.meta = meta;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
