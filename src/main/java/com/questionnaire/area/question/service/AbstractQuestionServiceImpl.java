package com.questionnaire.area.question.service;

import com.questionnaire.area.question.repository.QuestionRepository;

/**
 * Created by ChaosFire on 23.4.2017 г
 */
public abstract class AbstractQuestionServiceImpl {

    private final QuestionRepository questionRepository;

    protected AbstractQuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    protected QuestionRepository getQuestionRepository() {
        return this.questionRepository;
    }
}