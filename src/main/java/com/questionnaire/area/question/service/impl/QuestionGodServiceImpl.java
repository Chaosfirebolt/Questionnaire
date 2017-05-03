package com.questionnaire.area.question.service.impl;

import com.questionnaire.area.answer.repository.AnswerRepository;
import com.questionnaire.area.question.dto.bind.QuestionBind;
import com.questionnaire.area.question.repository.QuestionRepository;
import com.questionnaire.area.question.service.interfaces.QuestionGodService;
import com.questionnaire.area.reference.repository.AnswerReferenceRepository;
import com.questionnaire.area.trueAnswer.repository.TrueAnswerRepository;
import com.questionnaire.area.user.repository.UserRepository;
import com.questionnaire.io.newQuestions.QuestionsReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ChaosFire on 28.4.2017 г
 */
@Service
@Qualifier("god")
public class QuestionGodServiceImpl extends QuestionCrudServiceImpl implements QuestionGodService {

    @Autowired
    public QuestionGodServiceImpl(QuestionRepository questionRepository, UserRepository userRepository,
                                  AnswerRepository answerRepository, AnswerReferenceRepository answerReferenceRepository,
                                  TrueAnswerRepository trueAnswerRepository) {
        super(questionRepository, userRepository, answerRepository, answerReferenceRepository, trueAnswerRepository);
    }

    @Override
    public void validateOwnQuestions(long id) {
        super.getQuestionRepository().validateOwnQuestions(id);
    }

    @Transactional
    @Override
    public void addNewQuestions(String authorUsername) {
        List<QuestionBind> newQuestions = QuestionsReader.getNewQuestions();
        for (QuestionBind newQuestion : newQuestions) {
            super.addQuestion(newQuestion, authorUsername);
        }
    }
}