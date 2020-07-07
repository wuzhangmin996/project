package com.wu.project20.Result;

import com.wu.project20.bean.operation;
import com.wu.project20.bean.Meta;

import java.util.List;

public class ResultOperation {
    private List<operation> operationexam ;
    private Meta meta ;
    private String  title;

    public ResultOperation(List<operation> operationexam, Meta meta, String title) {
        this.operationexam = operationexam;
        this.meta = meta;
        this.title = title;
    }

    public List<operation> getOperationexam() {
        return operationexam;
    }

    public void setOperationexam(List<operation> operationexam) {
        this.operationexam = operationexam;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
