package com.questionnaire.area.game.service.impl;

import com.questionnaire.area.game.entity.Game;
import com.questionnaire.area.game.repository.GameRepository;
import com.questionnaire.area.game.service.AbstractGameServiceImpl;
import com.questionnaire.area.game.service.interfaces.GameInterceptorService;
import com.questionnaire.area.user.entity.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public boolean canPlayGame(long gameId) {
        Game game = super.getGameRepository().findOne(gameId);
        boolean isFinished = game.getFinished();

        Date now = new Date();
        Date timeLimit = game.getTimeLimit();
        boolean isTimeAfterLimit = false;
        if (timeLimit != null) {
            isTimeAfterLimit = now.after(timeLimit);
        }

        boolean isOriginalUser = true;
        AbstractUser user = (AbstractUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null || !user.getId().equals(game.getUser().getId())) {
            isOriginalUser = false;
        }

        return !isFinished && !isTimeAfterLimit && isOriginalUser;
    }
}
