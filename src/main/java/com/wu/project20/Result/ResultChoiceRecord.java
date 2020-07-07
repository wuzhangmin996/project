package com.wu.project20.Result;

import com.wu.project20.bean.Meta;
import com.wu.project20.bean.Scores;
import com.wu.project20.bean.Time;
import com.wu.project20.bean.choiceRecord;

import java.util.List;

public class ResultChoiceRecord {
    private String examName ;
    private List<Time> timeList;
    private List<choiceRecord> choiceList;
    private Meta meta ;
    private List<Scores> scores;
    private String examScore ;

    public ResultChoiceRecord(String examName, List<Time> timeList, List<choiceRecord> choiceList, Meta meta, List<Scores> scores, String examScore) {
        this.examName = examName;
        this.timeList = timeList;
        this.choiceList = choiceList;
        this.meta = meta;
        this.scores = scores;
        this.examScore = examScore;
    }

    public String getExamScore() {
        return examScore;
    }

    public void setExamScore(String examScore) {
        this.examScore = examScore;
    }

    public List<Time> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Time> timeList) {
        this.timeList = timeList;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public List<choiceRecord> getChoiceList() {
        return choiceList;
    }

    public void setChoiceList(List<choiceRecord> choiceList) {
        this.choiceList = choiceList;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Scores> getScores() {
        return scores;
    }

    public void setScores(List<Scores> scores) {
        this.scores = scores;
    }
}
