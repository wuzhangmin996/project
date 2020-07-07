package com.wu.project20.Result;

import com.wu.project20.bean.Meta;
import com.wu.project20.bean.question;

import java.util.List;

public class ResultTrain {
    private String trainName;
    private String trainStartTime;
    private String trainScore;
    private List<question> choiceTrain;
    private Meta meta ;

    public ResultTrain(String trainName, String trainStartTime, String trainScore, List<question> choiceTrain, Meta meta) {
        this.trainName = trainName;
        this.trainStartTime = trainStartTime;
        this.trainScore = trainScore;
        this.choiceTrain = choiceTrain;
        this.meta = meta;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }



    public String getTrainStartTime() {
        return trainStartTime;
    }

    public void setTrainStartTime(String trainStartTime) {
        this.trainStartTime = trainStartTime;
    }

    public String getTrainScore() {
        return trainScore;
    }

    public void setTrainScore(String trainScore) {
        this.trainScore = trainScore;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public List<question> getChoiceTrain() {
        return choiceTrain;
    }

    public void setChoiceTrain(List<question> choiceTrain) {
        this.choiceTrain = choiceTrain;
    }
}
