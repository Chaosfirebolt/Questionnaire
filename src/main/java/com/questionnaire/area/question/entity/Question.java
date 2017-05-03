package com.questionnaire.area.question.entity;

import com.questionnaire.area.answer.entity.Answer;
import com.questionnaire.area.reference.entity.AnswerReference;
import com.questionnaire.area.user.entity.AbstractUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ChaosFire on 15.4.2017 г
 */
@Entity
@Table(name = "questions")
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "question_text", unique = true, nullable = false)
    private String questionText;
    @Column(name = "question_level", nullable = false)
    private Byte questionLevel;
    @ManyToMany
    @JoinTable(name = "questions_answers",
                joinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "answer_id", referencedColumnName = "id"))
    private Set<Answer> possibleAnswers;
    @OneToOne
    @JoinColumn(name = "reference_id", referencedColumnName = "id", nullable = false)
    private AnswerReference answerReference;
    @Column(name = "is_validated", nullable = false)
    private Boolean isValidated;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false, updatable = false)
    private AbstractUser author;

    public Question() {
        super();
        this.setPossibleAnswers(new HashSet<>());
    }

    public Question(String questionText, Byte questionLevel, Set<Answer> possibleAnswers, AnswerReference answerReference,
                    Boolean isValidated, AbstractUser author) {
        this.setQuestionText(questionText);
        this.setQuestionLevel(questionLevel);
        this.setPossibleAnswers(possibleAnswers);
        this.setAnswerReference(answerReference);
        this.setValidated(isValidated);
        this.setAuthor(author);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Byte getQuestionLevel() {
        return this.questionLevel;
    }

    public void setQuestionLevel(Byte questionLevel) {
        this.questionLevel = questionLevel;
    }

    public Set<Answer> getPossibleAnswers() {
        return Collections.unmodifiableSet(this.possibleAnswers);
    }

    public void setPossibleAnswers(Set<Answer> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public AnswerReference getAnswerReference() {
        return this.answerReference;
    }

    public void setAnswerReference(AnswerReference answerReference) {
        this.answerReference = answerReference;
    }

    public Boolean getValidated() {
        return this.isValidated;
    }

    public void setValidated(Boolean validated) {
        this.isValidated = validated;
    }

    public AbstractUser getAuthor() {
        return this.author;
    }

    public void setAuthor(AbstractUser author) {
        this.author = author;
    }
}