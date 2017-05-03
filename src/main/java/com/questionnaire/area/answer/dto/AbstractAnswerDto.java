package com.questionnaire.area.answer.dto;

import com.questionnaire.util.constants.error.QuestionInputError;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by ChaosFire on 22.4.2017 г
 */
public abstract class AbstractAnswerDto implements Serializable {

    private static final int MIN_ANSWER_LENGTH = 1;

    @Size(min = MIN_ANSWER_LENGTH, message = QuestionInputError.ANSWER_ERROR)
    @NotNull
    private String answerText;

    protected AbstractAnswerDto() {
        super();
    }

    protected AbstractAnswerDto(String answerText) {
        this.setAnswerText(answerText);
    }

    public String getAnswerText() {
        return this.answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}