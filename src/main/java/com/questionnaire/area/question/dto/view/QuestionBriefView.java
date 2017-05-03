package com.questionnaire.area.question.dto.view;

import com.questionnaire.area.question.dto.AbstractQuestionDto;

/**
 * Created by ChaosFire on 24.4.2017 г
 */
public class QuestionBriefView extends AbstractQuestionDto {

    private long id;

    public QuestionBriefView() {
        super();
    }

    public QuestionBriefView(long id, String questionText) {
        super(questionText);
        this.setId(id);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}