package com.wu.project20.Result;

import com.wu.project20.bean.Meta;
import com.wu.project20.bean.choiceRecord;
public class ResultChoice {
    private choiceRecord choiceRecord ;
    private Meta meta;

    public ResultChoice(choiceRecord choiceRecord, Meta meta) {
        this.choiceRecord = choiceRecord;
        this.meta = meta;
    }

    public choiceRecord getChoiceRecord() {
        return choiceRecord;
    }

    public void setChoiceRecord(choiceRecord choiceRecord) {
        this.choiceRecord = choiceRecord;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
