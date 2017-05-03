package com.questionnaire.area.question.service.interfaces;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by ChaosFire on 28.4.2017 г
 */
@PreAuthorize("hasRole('GOD')")
public interface QuestionGodService {

    void validateOwnQuestions(long id);
    void addNewQuestions(String authorUsername);
}