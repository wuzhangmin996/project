package com.wu.project20.bean;

import java.util.Date;
import java.util.List;

public class AddTrain {
    private String trainname;
    private Date trainstarttime;
    private List<select> selectdata;
    private String trainscore ;

    public Date getTrainstarttime() {
        return trainstarttime;
    }

    public void setTrainstarttime(Date trainstarttime) {
        this.trainstarttime = trainstarttime;
    }

    public String getTrainscore() {
        return trainscore;
    }

    public void setTrainscore(String trainscore) {
        this.trainscore = trainscore;
    }

    public String getTrainname() {
        return trainname;
    }

    public void setTrainname(String trainname) {
        this.trainname = trainname;
    }

    public List<select> getSelectdata() {
        return selectdata;
    }

    public void setSelectdata(List<select> selectdata) {
        this.selectdata = selectdata;
    }
}
