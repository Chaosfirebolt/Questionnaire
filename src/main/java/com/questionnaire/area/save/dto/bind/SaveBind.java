package com.questionnaire.area.save.dto.bind;

import com.questionnaire.area.save.dto.AbstractSaveDto;

import java.util.Date;

/**
 * Created by ChaosFire on 1.5.2017 г
 */
public class SaveBind extends AbstractSaveDto {

    private long gameId;

    public SaveBind() {
        super(new Date());
    }

    public SaveBind(long gameId) {
        super(new Date());
        this.setGameId(gameId);
    }

    public long getGameId() {
        return this.gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }
}