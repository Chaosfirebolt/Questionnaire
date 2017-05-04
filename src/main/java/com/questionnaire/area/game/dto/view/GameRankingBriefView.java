package com.questionnaire.area.game.dto.view;

import com.questionnaire.area.game.dto.view.abstraction.AbstractGameView;

/**
 * Created by ChaosFire on 4.5.2017 г
 */
public class GameRankingBriefView extends AbstractGameView {

    private String totalTime;

    public GameRankingBriefView() {
        super();
    }

    public GameRankingBriefView(String username, byte currentQuestion, String totalTime) {
        super(username, currentQuestion);
        this.setTotalTime(totalTime);
    }

    public String getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
}