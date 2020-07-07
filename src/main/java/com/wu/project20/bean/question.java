package com.wu.project20.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class question {
    private int choicequestionid ;
    private String description ;
    private String choicea;
    private String choiceb;
    private String choicec;
    private String choiced;
    private int score ;
    private String questionstarttime;
    private String questionendtime ;

    public String getQuestionstarttime() {
        return questionstarttime;
    }

    public void setQuestionstarttime(Date questionstime) {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  time=sim.format(questionstime);
        this.questionstarttime = time ;

    }

    public String getQuestionendtime() {
        return questionendtime;
    }

    public void setQuestionendtime(Date questionetime) {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  time=sim.format(questionetime);
        this.questionendtime = time ;

    }



    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getChoicequestionid() {
        return choicequestionid;
    }

    public void setChoicequestionid(int choicequestionid) {
        this.choicequestionid = choicequestionid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChoicea() {
        return choicea;
    }

    public void setChoicea(String choicea) {
        this.choicea = choicea;
    }

    public String getChoiceb() {
        return choiceb;
    }

    public void setChoiceb(String choiceb) {
        this.choiceb = choiceb;
    }

    public String getChoicec() {
        return choicec;
    }

    public void setChoicec(String choicec) {
        this.choicec = choicec;
    }

    public String getChoiced() {
        return choiced;
    }

    public void setChoiced(String choiced) {
        this.choiced = choiced;
    }


}
