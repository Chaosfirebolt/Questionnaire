package com.questionnaire.area.trueAnswer.service;

/**
 * Created by ChaosFire on 30.4.2017 г
 */
public interface TrueAnswerService {

    boolean verifyAnswer(long questionId, long answerId);
}