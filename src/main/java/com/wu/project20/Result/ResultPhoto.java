package com.wu.project20.Result;

import com.wu.project20.bean.Meta;
import com.wu.project20.bean.homephoto;

import java.util.List;

public class ResultPhoto {
    private List<homephoto> homephotos ;
    private Meta meta ;

    public ResultPhoto(List<homephoto> homephotos, Meta meta) {
        this.homephotos = homephotos;
        this.meta = meta;
    }

    public List<homephoto> getHomephotos() {
        return homephotos;
    }

    public void setHomephotos(List<homephoto> homephotos) {
        this.homephotos = homephotos;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
