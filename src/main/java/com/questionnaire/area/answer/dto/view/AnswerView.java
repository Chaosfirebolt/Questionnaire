package com.questionnaire.area.answer.dto.view;

import com.questionnaire.area.answer.dto.AbstractAnswerDto;

/**
 * Created by ChaosFire on 22.4.2017 г
 */
public class AnswerView extends AbstractAnswerDto {

    private long id;

    public AnswerView() {
        super();
    }

    public AnswerView(long id, String answerText) {
        super(answerText);
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}