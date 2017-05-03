package com.questionnaire.area.question.service.interfaces;

import com.questionnaire.area.question.dto.view.QuestionView;

/**
 * Created by ChaosFire on 23.4.2017 г
 */
public interface QuestionGameService {

    QuestionView chooseNextQuestion(byte questionLevel, long gameId);
}