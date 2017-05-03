package com.questionnaire.area.question.service.interfaces;

import com.questionnaire.area.question.dto.bind.QuestionBind;
import com.questionnaire.area.question.dto.view.QuestionBriefView;
import com.questionnaire.area.question.dto.view.QuestionValidationView;

import java.util.List;

/**
 * Created by ChaosFire on 23.4.2017 г
 */
public interface QuestionCrudService {

    void addQuestion(QuestionBind questionBind, String authorUsername);
    void validateQuestion(long id);
    List<QuestionBriefView> getNotValidatedQuestions(long authorId);
    QuestionValidationView showQuestionToValidate(long id);
}