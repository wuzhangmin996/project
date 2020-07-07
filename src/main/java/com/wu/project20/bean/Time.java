package com.wu.project20.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    private String submittime;
    private String examendtime ;
    private String examstarttime ;
    private String spendtime ;

    public String getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  time=sim.format(submittime);
        this.submittime = time;
    }

    public String getExamendtime() {
        return examendtime;
    }

    public void setExamendtime(Date examendtime) {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  time=sim.format(examendtime);
        this.examendtime = time ;
    }

    public String getExamstarttime() {
        return examstarttime;
    }

    public void setExamstarttime(Date examstarttime) {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  time=sim.format(examstarttime);
        this.examstarttime = time;
    }

    public String getSpendtime() {
        return spendtime;
    }

    public void setSpendtime(String examstarttime , String submittime)  {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timestart = formatter.parse(examstarttime);
            Date submit = formatter.parse(submittime);
            long diff = submit.getTime() - timestart.getTime();
            long days = diff / (1000 * 60 * 60 * 24);

            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
            spendtime = days+"天"+hours+"小时"+minutes+"分";
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
