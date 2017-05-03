package com.questionnaire.area.game.dto.view;

import com.questionnaire.area.game.dto.view.abstraction.AbstractGameView;

/**
 * Created by ChaosFire on 29.4.2017 г
 */
public class GameView extends AbstractGameView {

    private long gameId;

    public GameView() {
        super();
    }

    public GameView(long gameId, String username, byte currentQuestion) {
        super(username, currentQuestion);
        this.setGameId(gameId);
    }

    public long getGameId() {
        return this.gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }
}