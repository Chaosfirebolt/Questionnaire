package com.questionnaire.area.game.dto.view;

/**
 * Created by ChaosFire on 1.5.2017 г
 */
public class GameRankingView extends GameRankingBriefView {

    private long position;

    public GameRankingView() {
        super();
    }

    public GameRankingView(long position, String username, byte currentQuestion, String totalTime) {
        super(username, currentQuestion, totalTime);
        this.setPosition(position);
    }

    public long getPosition() {
        return this.position;
    }

    public void setPosition(long position) {
        this.position = position;
    }
}