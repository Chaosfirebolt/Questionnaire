package com.questionnaire.area.trueAnswer.service;

import com.questionnaire.area.trueAnswer.repository.TrueAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ChaosFire on 30.4.2017 г
 */
@Service
public class TrueAnswerServiceImpl implements TrueAnswerService {

    private final TrueAnswerRepository trueAnswerRepository;

    @Autowired
    public TrueAnswerServiceImpl(TrueAnswerRepository trueAnswerRepository) {
        this.trueAnswerRepository = trueAnswerRepository;
    }

    @Override
    public boolean verifyAnswer(long questionId, long answerId) {
        long trueAnswerId = this.trueAnswerRepository.findOneByQuestionId(questionId).getTrueAnswer().getId();
        return trueAnswerId == answerId;
    }
}