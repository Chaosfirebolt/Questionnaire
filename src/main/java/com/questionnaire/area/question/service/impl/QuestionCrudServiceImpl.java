package com.questionnaire.area.question.service.impl;

import com.questionnaire.area.answer.dto.bind.AnswerBind;
import com.questionnaire.area.answer.dto.view.AnswerValidationView;
import com.questionnaire.area.answer.entity.Answer;
import com.questionnaire.area.answer.repository.AnswerRepository;
import com.questionnaire.area.question.dto.bind.QuestionBind;
import com.questionnaire.area.question.dto.view.QuestionBriefView;
import com.questionnaire.area.question.dto.view.QuestionValidationView;
import com.questionnaire.area.question.entity.Question;
import com.questionnaire.area.question.repository.QuestionRepository;
import com.questionnaire.area.question.service.AbstractQuestionServiceImpl;
import com.questionnaire.area.question.service.interfaces.QuestionCrudService;
import com.questionnaire.area.reference.entity.AnswerReference;
import com.questionnaire.area.reference.repository.AnswerReferenceRepository;
import com.questionnaire.area.trueAnswer.entity.TrueAnswer;
import com.questionnaire.area.trueAnswer.repository.TrueAnswerRepository;
import com.questionnaire.area.user.entity.AbstractUser;
import com.questionnaire.area.user.repository.UserRepository;
import com.questionnaire.exception.question.NonExistentQuestionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ChaosFire on 23.4.2017 г
 */
@Service
@Qualifier("crud")
public class QuestionCrudServiceImpl extends AbstractQuestionServiceImpl implements QuestionCrudService {

    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final AnswerReferenceRepository answerReferenceRepository;
    private final TrueAnswerRepository trueAnswerRepository;

    @Autowired
    public QuestionCrudServiceImpl(QuestionRepository questionRepository, UserRepository userRepository, AnswerRepository answerRepository,
                               AnswerReferenceRepository answerReferenceRepository, TrueAnswerRepository trueAnswerRepository) {
        super(questionRepository);
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.answerReferenceRepository = answerReferenceRepository;
        this.trueAnswerRepository = trueAnswerRepository;
    }

    @Transactional
    @Override
    public void addQuestion(QuestionBind questionBind, String authorUsername) {
        AbstractUser author = this.userRepository.findOneByUsername(authorUsername);

        AnswerReference reference = new AnswerReference(questionBind.getAnswerReference().getUrl());
        reference = this.answerReferenceRepository.saveAndFlush(reference);

        TrueAnswer trueAnswer = new TrueAnswer();

        List<AnswerBind> answerBindList = questionBind.getPossibleAnswers();
        Set<Answer> answers = new HashSet<>();
        for (AnswerBind answerBind : answerBindList) {
            Answer answer = new Answer(answerBind.getAnswerText());
            answer = this.answerRepository.saveAndFlush(answer);
            if (answerBind.isTrue()) {
                trueAnswer.setTrueAnswer(answer);
            }
            answers.add(answer);
        }

        final boolean DEFAULT_IS_VALID = false;
        String text = questionBind.getQuestionText();
        byte level = questionBind.getLevel();
        Question question = new Question(text, level, answers, reference, DEFAULT_IS_VALID, author);
        question = super.getQuestionRepository().saveAndFlush(question);

        trueAnswer.setQuestion(question);
        this.trueAnswerRepository.saveAndFlush(trueAnswer);
    }

    @Override
    public void validateQuestion(long id) {
        super.getQuestionRepository().validateQuestion(id);
    }

    @Override
    public List<QuestionBriefView> getNotValidatedQuestions(long authorId) {
        List<Question> notValidatedQuestions = super.getQuestionRepository().findNotValidated(authorId);
        List<QuestionBriefView> notValidatedViewList = new ArrayList<>();
        for (Question question : notValidatedQuestions) {
            QuestionBriefView questionView = new QuestionBriefView(question.getId(), question.getQuestionText());
            notValidatedViewList.add(questionView);
        }
        return notValidatedViewList;
    }

    @Override
    public QuestionValidationView showQuestionToValidate(long id) {
        Question question = super.getQuestionRepository().findOne(id);
        if (question == null) {
            throw new NonExistentQuestionException();
        }
        String text = question.getQuestionText();
        byte level = question.getQuestionLevel();

        Set<Answer> answers = question.getPossibleAnswers();
        List<AnswerValidationView> answerViewList = new ArrayList<>();
        long trueAnswerId = this.trueAnswerRepository.findOneByQuestionId(id).getTrueAnswer().getId();
        for (Answer answer : answers) {
            long answerId = answer.getId();
            boolean isTrue = trueAnswerId == answerId;
            AnswerValidationView answerView = new AnswerValidationView(answerId, answer.getAnswerText(), isTrue);
            answerViewList.add(answerView);
        }

        String reference = question.getAnswerReference().getUrl();

        return new QuestionValidationView(id, text, level, answerViewList, reference);
    }
}