package com.questionnaire.area.save.dto.view;

import com.questionnaire.area.save.dto.AbstractSaveDto;

import java.util.Date;

/**
 * Created by ChaosFire on 1.5.2017 г
 */
public class SaveView extends AbstractSaveDto {

    private long saveId;

    public SaveView() {
        super();
    }

    public SaveView(long saveId, Date time) {
        super(time);
        this.setSaveId(saveId);
    }

    public long getSaveId() {
        return this.saveId;
    }

    public void setSaveId(long saveId) {
        this.saveId = saveId;
    }
}