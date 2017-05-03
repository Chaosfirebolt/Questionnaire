package com.questionnaire.area.answer.dto.view;

/**
 * Created by ChaosFire on 26.4.2017 г
 */
public class AnswerValidationView extends AnswerView {

    private boolean isTrue;

    public AnswerValidationView() {
        super();
    }

    public AnswerValidationView(long id, String answerText, boolean isTrue) {
        super(id, answerText);
        this.setTrue(isTrue);
    }

    public boolean isTrue() {
        return this.isTrue;
    }

    public void setTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }
}