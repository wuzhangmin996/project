package com.wu.project20.Result;

import com.wu.project20.bean.Meta;

public class ResultRole {
    private String role ;
    private Meta meta ;

    public ResultRole(String role, Meta meta) {
        this.role = role;
        this.meta = meta;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
