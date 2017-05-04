package com.questionnaire.area.game.service.interfaces;

import com.questionnaire.area.game.dto.bind.GameBind;
import com.questionnaire.area.game.dto.view.GameView;

import java.util.Date;

/**
 * Created by ChaosFire on 28.4.2017 г
 */
public interface GameService {

    GameView startNewGame(GameBind gameBind);
    GameView getGame(long gameId);
    void finishGame(long gameId);
    void updateGame(long answerTime, long gameId);
    void addTimeLimitToAnswer(Date timeLimit, long gameId);
    void addQuestionToAnswered(long gameId, long questionsId);
}