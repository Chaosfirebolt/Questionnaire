package com.questionnaire.area.answer.dto.bind;

import com.questionnaire.area.answer.dto.AbstractAnswerDto;

/**
 * Created by ChaosFire on 22.4.2017 г
 */
public class AnswerBind extends AbstractAnswerDto {

    private boolean isTrue;

    public AnswerBind() {
        super();
    }

    public AnswerBind(String answerText, boolean isTrue) {
        super(answerText);
        this.setTrue(isTrue);
    }

    public boolean isTrue() {
        return this.isTrue;
    }

    public void setTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }
}