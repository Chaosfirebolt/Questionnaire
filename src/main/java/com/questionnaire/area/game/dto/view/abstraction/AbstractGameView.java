package com.questionnaire.area.game.dto.view.abstraction;

import com.questionnaire.area.game.dto.AbstractGameDto;

/**
 * Created by ChaosFire on 1.5.2017 г
 */
public abstract class AbstractGameView extends AbstractGameDto {

    private byte currentQuestion;

    protected AbstractGameView() {
        super();
    }

    protected AbstractGameView(String username, byte currentQuestion) {
        super(username);
        this.setCurrentQuestion(currentQuestion);
    }

    public byte getCurrentQuestion() {
        return this.currentQuestion;
    }

    public void setCurrentQuestion(byte currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}