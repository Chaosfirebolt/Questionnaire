package com.questionnaire.area.question.service.impl;

import com.questionnaire.area.answer.dto.view.AnswerView;
import com.questionnaire.area.answer.entity.Answer;
import com.questionnaire.area.game.repository.GameRepository;
import com.questionnaire.area.question.dto.view.QuestionView;
import com.questionnaire.area.question.entity.Question;
import com.questionnaire.area.question.repository.QuestionRepository;
import com.questionnaire.area.question.service.AbstractQuestionServiceImpl;
import com.questionnaire.area.question.service.interfaces.QuestionGameService;
import com.questionnaire.util.collection.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ChaosFire on 23.4.2017 г
 */
@Service
public class QuestionGameServiceImpl extends AbstractQuestionServiceImpl implements QuestionGameService {

    private final GameRepository gameRepository;

    @Autowired
    public QuestionGameServiceImpl(QuestionRepository questionRepository, GameRepository gameRepository) {
        super(questionRepository);
        this.gameRepository = gameRepository;
    }

    @Override
    public QuestionView chooseNextQuestion(byte questionLevel, long gameId) {
        List<Long> validQuestionsIdList = super.getQuestionRepository().findValidQuestionsId(questionLevel);
        List<Long> answeredQuestionsIdList = this.gameRepository.findAnsweredQuestionsIdForGame(gameId);
        validQuestionsIdList = validQuestionsIdList.stream()
                .filter(qId -> !answeredQuestionsIdList.contains(qId))
                .collect(Collectors.toList());

        Random random = new Random();
        int randomIndex = random.nextInt(validQuestionsIdList.size());
        long randomQuestionId = validQuestionsIdList.get(randomIndex);

        Question question = super.getQuestionRepository().findOne(randomQuestionId);

        Set<Answer> possibleAnswers = question.getPossibleAnswers();
        List<AnswerView> answerViewList = new ArrayList<>();
        for (Answer answer : possibleAnswers) {
            AnswerView answerView = new AnswerView(answer.getId(), answer.getAnswerText());
            answerViewList.add(answerView);
        }
        List<AnswerView> shuffledAnswers = CollectionUtil.shuffle(answerViewList);

        return new QuestionView(question.getId(), question.getQuestionText(), shuffledAnswers);
    }
}