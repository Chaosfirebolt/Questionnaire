package com.questionnaire.area.question.service.interfaces;

/**
 * Created by ChaosFire on 25.4.2017 г
 */
public interface QuestionInterceptorService {

    boolean isValidatorAuthor(long questionId, long validatorId);
}