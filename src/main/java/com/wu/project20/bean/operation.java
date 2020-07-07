package com.wu.project20.bean;

public class operation {
    private int operationquestionid;
    private String description;


    public int getOperationquestionid() {
        return operationquestionid;
    }

    public void setOperationquestionid(int operationquestionid) {
        this.operationquestionid = operationquestionid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "operation{" +
                "operationquestionid=" + operationquestionid +
                ", description='" + description + '\'' +
                '}';
    }
}
