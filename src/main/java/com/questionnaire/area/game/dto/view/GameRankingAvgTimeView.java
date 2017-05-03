package com.questionnaire.area.game.dto.view;

import com.questionnaire.area.game.dto.AbstractGameDto;

/**
 * Created by ChaosFire on 2.5.2017 г
 */
public class GameRankingAvgTimeView extends AbstractGameDto {

    private long position;
    private String averageAnswerTime;

    public GameRankingAvgTimeView() {
        super();
    }

    public GameRankingAvgTimeView(long position, String username, String averageAnswerTime) {
        super(username);
        this.setPosition(position);
        this.setAverageAnswerTime(averageAnswerTime);
    }

    public long getPosition() {
        return this.position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public String getAverageAnswerTime() {
        return this.averageAnswerTime;
    }

    public void setAverageAnswerTime(String averageAnswerTime) {
        this.averageAnswerTime = averageAnswerTime;
    }
}