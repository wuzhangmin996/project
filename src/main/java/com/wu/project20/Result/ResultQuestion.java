package com.wu.project20.Result;

import com.wu.project20.bean.Meta;
import com.wu.project20.bean.operation;
import com.wu.project20.bean.question;

import java.util.List;
public class ResultQuestion {
    String examName ;
    private List<operation>  operationExam;
    private List<question> choiceExam;
    private Meta meta;
    private String  examEndTime  ;
    private String examScore;

    public String getExamScore() {
        return examScore;
    }

    public void setExamScore(String examScore) {
        this.examScore = examScore;
    }

    public ResultQuestion(String examName, List<operation> operationExam, List<question> choiceExam, Meta meta, String examEndTime, String examScore) {
        this.examName = examName;
        this.operationExam = operationExam;
        this.choiceExam = choiceExam;
        this.meta = meta;
        this.examEndTime = examEndTime;
        this.examScore = examScore;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public List<operation> getOperationExam() {
        return operationExam;
    }

    public void setOperationExam(List<operation> operationExam) {
        this.operationExam = operationExam;
    }

    public List<question> getChoiceExam() {
        return choiceExam;
    }

    public void setChoiceExam(List<question> choiceExam) {
        this.choiceExam = choiceExam;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getExamEndTime() {
        return examEndTime;
    }

    public void setExamEndTime(String examEndTime) {
        this.examEndTime = examEndTime;
    }
}
