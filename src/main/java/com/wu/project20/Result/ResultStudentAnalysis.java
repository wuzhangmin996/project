package com.wu.project20.Result;

import com.wu.project20.bean.Meta;
import com.wu.project20.bean.StudentAnalysis;

import java.util.List;

public class ResultStudentAnalysis {

    private String examName;
    private List<StudentAnalysis> scoreList ;
    private Meta meta ;
    private String averageScore ;

    public ResultStudentAnalysis(String examName, List<StudentAnalysis> scoreList, Meta meta, String averageScore) {
        this.examName = examName;
        this.scoreList = scoreList;
        this.meta = meta;
        this.averageScore = averageScore;
    }

    public String getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(String averageScore) {
        this.averageScore = averageScore;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public List<StudentAnalysis> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<StudentAnalysis> scoreList) {
        this.scoreList = scoreList;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
