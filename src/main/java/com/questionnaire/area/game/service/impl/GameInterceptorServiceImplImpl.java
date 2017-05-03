package com.questionnaire.area.game.service.impl;

import com.questionnaire.area.game.entity.Game;
import com.questionnaire.area.game.repository.GameRepository;
import com.questionnaire.area.game.service.AbstractGameServiceImpl;
import com.questionnaire.area.game.service.interfaces.GameInterceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ChaosFire on 30.4.2017 г
 */
@Service
public class GameInterceptorServiceImplImpl extends AbstractGameServiceImpl implements GameInterceptorService {

    @Autowired
    public GameInterceptorServiceImplImpl(GameRepository gameRepository) {
        super(gameRepository);
    }

    @Override
    public boolean checkIsGameFinished(long gameId) {
        Game game = super.getGameRepository().findOne(gameId);
        return game.getFinished();
    }
}
