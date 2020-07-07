package com.wu.project20.bean;

import java.util.List;

public class firstMenu {
    private String  fid ;
    private String fname ;
    private String path ;
    private int iconid ;
    private List<secondMenu> children;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIconid() {
        return iconid;
    }

    public void setIconid(int iconid) {
        this.iconid = iconid;
    }

    public List<secondMenu> getChildren() {
        return children;
    }

    public void setChildren(List<secondMenu> children) {
        this.children = children;
    }
}
