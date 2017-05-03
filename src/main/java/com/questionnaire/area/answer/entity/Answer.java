package com.questionnaire.area.answer.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ChaosFire on 13.4.2017 г
 */
@Entity
@Table(name = "answers")
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "answer_text", nullable = false)
    private String answerText;

    public Answer() {
        super();
    }

    public Answer(String answerText) {
        this.setAnswerText(answerText);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerText() {
        return this.answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}