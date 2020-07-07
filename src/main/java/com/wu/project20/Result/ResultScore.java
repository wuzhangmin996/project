package com.wu.project20.Result;

import com.wu.project20.bean.Meta;

public class ResultScore {
    private int  score ;
    private Meta meta ;
    public ResultScore(int score, Meta meta) {
        this.score = score;
        this.meta = meta;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
