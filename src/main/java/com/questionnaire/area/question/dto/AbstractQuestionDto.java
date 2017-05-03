package com.questionnaire.area.question.dto;

import com.questionnaire.util.constants.error.QuestionInputError;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by ChaosFire on 22.4.2017 г
 */
public abstract class AbstractQuestionDto implements Serializable {

    private static final int MIN_QUESTION_LENGTH = 10;

    @Size(min = MIN_QUESTION_LENGTH, message = QuestionInputError.QUESTION_ERROR)
    private String questionText;

    public AbstractQuestionDto() {
        super();
    }

    public AbstractQuestionDto(String questionText) {
        this.setQuestionText(questionText);
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}