package com.wu.project20.Result;

import com.wu.project20.bean.Meta;
import com.wu.project20.bean.choiceRecord;

import java.util.List;

public class ResultChoiceQuestionList {
    private List<choiceRecord> choiceQuestionList ;
    private Meta meta ;
    private int totalNum;
    private String  pageNum;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public List<choiceRecord> getChoiceQuestionList() {
        return choiceQuestionList;
    }

    public void setChoiceQuestionList(List<choiceRecord> choiceQuestionList) {
        this.choiceQuestionList = choiceQuestionList;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ResultChoiceQuestionList(int totalNum,String pagenum ,List<choiceRecord> choiceQuestionList, Meta meta) {
        this.totalNum  = totalNum ;
        this.pageNum = pagenum ;
        this.choiceQuestionList = choiceQuestionList;
        this.meta = meta;
    }
}
