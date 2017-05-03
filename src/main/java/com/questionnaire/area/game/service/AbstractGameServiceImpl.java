package com.questionnaire.area.game.service;

import com.questionnaire.area.game.repository.GameRepository;

/**
 * Created by ChaosFire on 30.4.2017 г
 */
public abstract class AbstractGameServiceImpl {

    private final GameRepository gameRepository;

    protected AbstractGameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    protected GameRepository getGameRepository() {
        return this.gameRepository;
    }
}