package com.questionnaire.area.game.dto.view;

import com.questionnaire.area.game.dto.view.abstraction.AbstractGameView;

/**
 * Created by ChaosFire on 1.5.2017 г
 */
public class GameRankingView extends AbstractGameView {

    private long position;
    private String totalTime;

    public GameRankingView() {
        super();
    }

    public GameRankingView(long position, String username, byte currentQuestion, String totalTime) {
        super(username, currentQuestion);
        this.setPosition(position);
        this.setTotalTime(totalTime);
    }

    public long getPosition() {
        return this.position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public String getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
}