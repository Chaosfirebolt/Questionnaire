package com.questionnaire.area.save.dto;

import java.util.Date;

/**
 * Created by ChaosFire on 1.5.2017 г
 */
public abstract class AbstractSaveDto {

    private Date time;

    protected AbstractSaveDto() {
        super();
    }

    protected AbstractSaveDto(Date time) {
        this.setTime(time);
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}