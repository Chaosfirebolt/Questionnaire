package com.questionnaire.area.game.dto;

import java.io.Serializable;

/**
 * Created by ChaosFire on 28.4.2017 г
 */
public abstract class AbstractGameDto implements Serializable {

    private String username;

    protected AbstractGameDto() {
        super();
    }

    protected AbstractGameDto(String username) {
        this.setUsername(username);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}