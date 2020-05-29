package com.wu.project20.bean;

public class ResultScore {
    private Score score ;
    private Meta meta ;

    public ResultScore(Score score, Meta meta) {
        this.score = score;
        this.meta = meta;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
