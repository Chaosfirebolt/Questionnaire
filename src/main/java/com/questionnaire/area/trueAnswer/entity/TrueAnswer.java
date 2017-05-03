package com.questionnaire.area.trueAnswer.entity;

import com.questionnaire.area.answer.entity.Answer;
import com.questionnaire.area.question.entity.Question;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ChaosFire on 15.4.2017 г
 */
@Entity
@Table(name = "true_answers")
public class TrueAnswer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id", unique = true, nullable = false)
    private Question question;
    @OneToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "id", nullable = false)
    private Answer trueAnswer;

    public TrueAnswer() {
        super();
    }

    public TrueAnswer(Question question, Answer trueAnswer) {
        this.setQuestion(question);
        this.setTrueAnswer(trueAnswer);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getTrueAnswer() {
        return this.trueAnswer;
    }

    public void setTrueAnswer(Answer trueAnswer) {
        this.trueAnswer = trueAnswer;
    }
}