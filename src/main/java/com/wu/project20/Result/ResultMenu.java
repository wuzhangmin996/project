package com.wu.project20.Result;

import com.wu.project20.bean.Meta;
import com.wu.project20.bean.firstMenu;

import java.util.List;

public class ResultMenu {
    private List<firstMenu> menuList ;
    private Meta meta ;

    public ResultMenu(List<firstMenu> menuList, Meta meta) {
        this.menuList = menuList;
        this.meta = meta;
    }

    public List<firstMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<firstMenu> menuList) {
        this.menuList = menuList;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
