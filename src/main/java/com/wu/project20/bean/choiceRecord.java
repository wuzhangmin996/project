package com.wu.project20.bean;

public class choiceRecord {
    private int choicequestionid ;
    private String description;
    private String choicea;
    private String choiceb;
    private String choicec;
    private String choiced;
    private String answer ;
    private String choice;
    private String score ;


    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
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



    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "choiceRecord{" +
                "choicequestionid=" + choicequestionid +
                ", description='" + description + '\'' +
                ", choicea='" + choicea + '\'' +
                ", choiceb='" + choiceb + '\'' +
                ", choicec='" + choicec + '\'' +
                ", choiced='" + choiced + '\'' +
                ", answer='" + answer + '\'' +
                ", choice='" + choice + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
