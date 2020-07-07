package com.wu.project20.Result;

import com.wu.project20.bean.Exam;
import com.wu.project20.bean.Meta;

import java.util.List;

public class ResultExam {
    private int totalNum ;
    private List<Exam> examList ;
    private Meta meta ;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public ResultExam(int  totalNum, List<Exam> examList, Meta meta) {
        this.totalNum = totalNum;
        this.examList = examList;
        this.meta = meta;
    }

    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
