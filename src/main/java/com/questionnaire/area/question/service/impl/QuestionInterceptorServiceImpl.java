package com.questionnaire.area.question.service.impl;

import com.questionnaire.area.question.entity.Question;
import com.questionnaire.area.question.repository.QuestionRepository;
import com.questionnaire.area.question.service.AbstractQuestionServiceImpl;
import com.questionnaire.area.question.service.interfaces.QuestionInterceptorService;
import com.questionnaire.exception.question.NonExistentQuestionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ChaosFire on 25.4.2017 г
 */
@Service
public class QuestionInterceptorServiceImpl extends AbstractQuestionServiceImpl implements QuestionInterceptorService {

    @Autowired
    public QuestionInterceptorServiceImpl(QuestionRepository questionRepository) {
        super(questionRepository);
    }

    @Override
    public boolean isValidatorAuthor(long questionId, long validatorId) {
        Question question = super.getQuestionRepository().findOne(questionId);
        if (question == null) {
            throw new NonExistentQuestionException();
        }
        long authorId = question.getAuthor().getId();
        return authorId == validatorId;
    }
}