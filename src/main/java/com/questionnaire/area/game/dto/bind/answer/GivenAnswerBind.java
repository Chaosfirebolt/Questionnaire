package com.questionnaire.area.game.dto.bind.answer;

import java.io.Serializable;

/**
 * Created by ChaosFire on 30.4.2017 г
 */
public class GivenAnswerBind implements Serializable {

    private long questionId;
    private long answerId;

    public GivenAnswerBind() {
        super();
    }

    public GivenAnswerBind(long questionId, long answerId) {
        this.setQuestionId(questionId);
        this.setAnswerId(answerId);
    }

    public long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getAnswerId() {
        return this.answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }
}