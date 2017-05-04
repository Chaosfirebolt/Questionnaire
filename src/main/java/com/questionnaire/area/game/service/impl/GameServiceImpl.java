package com.questionnaire.area.game.service.impl;

import com.questionnaire.area.game.dto.bind.GameBind;
import com.questionnaire.area.game.dto.view.GameView;
import com.questionnaire.area.game.entity.Game;
import com.questionnaire.area.game.repository.GameRepository;
import com.questionnaire.area.game.service.AbstractGameServiceImpl;
import com.questionnaire.area.game.service.interfaces.GameService;
import com.questionnaire.area.question.entity.Question;
import com.questionnaire.area.question.repository.QuestionRepository;
import com.questionnaire.area.user.entity.AbstractUser;
import com.questionnaire.area.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by ChaosFire on 29.4.2017 г
 */
@Service
public class GameServiceImpl extends AbstractGameServiceImpl implements GameService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        super(gameRepository);
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public GameView startNewGame(GameBind gameBind) {
        AbstractUser user = this.userRepository.findOneByUsername(gameBind.getUsername());

        final byte DEF_CURR_QUESTION = 1;
        final boolean DEF_IS_FINISHED = false;
        final long DEF_TOTAL_TIME = 0;
        Game newGame = new Game(user, DEF_CURR_QUESTION, DEF_IS_FINISHED, DEF_TOTAL_TIME);
        newGame = super.getGameRepository().saveAndFlush(newGame);

        return new GameView(newGame.getId(), newGame.getUser().getUsername(), newGame.getCurrentQuestion());
    }

    @Override
    public GameView getGame(long gameId) {
        Game game = super.getGameRepository().findOne(gameId);
        return new GameView(game.getId(), game.getUser().getUsername(), game.getCurrentQuestion());
    }

    @Override
    public void finishGame(long gameId) {
        super.getGameRepository().finishGame(gameId);
    }

    @Override
    public void updateGame(long answerTime, long gameId) {
        super.getGameRepository().updateGame(answerTime, gameId);
    }

    @Override
    public void addTimeLimitToAnswer(Date timeLimit, long gameId) {
        super.getGameRepository().addAnswerTimeLimit(timeLimit, gameId);
    }

    @Override
    public void addQuestionToAnswered(long gameId, long questionsId) {
        Question question = this.questionRepository.findOne(questionsId);
        GameRepository gameRepository = super.getGameRepository();
        Game game = gameRepository.findOne(gameId);
        game.addAnsweredQuestion(question);
        gameRepository.saveAndFlush(game);
    }
}