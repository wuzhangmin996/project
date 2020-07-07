package com.wu.project20.Result;

import com.wu.project20.bean.Meta;

public class ResultToken {
    private String token ;
    private Meta meta ;
    public ResultToken(String token, Meta meta) {
        this.token = token ;
        this.meta = meta ;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
