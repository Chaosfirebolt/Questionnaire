package com.questionnaire.area.game.entity;

import com.questionnaire.area.question.entity.Question;
import com.questionnaire.area.user.entity.AbstractUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ChaosFire on 13.4.2017 г
 */
@Entity
@Table(name = "games")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private AbstractUser user;
    @Column(name = "current_question", nullable = false)
    private Byte currentQuestion;
    @Column(name = "is_finished", nullable = false)
    private Boolean isFinished;
    @Column(name = "total_time")
    private Long totalTime;
    @ManyToMany
    @JoinTable(name = "games_questions",
                joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private Set<Question> answeredQuestions;

    public Game() {
        super();
        this.setAnsweredQuestions(new HashSet<>());
    }

    public Game(AbstractUser user, Byte currentQuestion, Boolean isFinished, Long totalTime) {
        this.setUser(user);
        this.setCurrentQuestion(currentQuestion);
        this.setFinished(isFinished);
        this.setTotalTime(totalTime);
        this.setAnsweredQuestions(new HashSet<>());
    }

    public void addAnsweredQuestion(Question question) {
        this.answeredQuestions.add(question);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AbstractUser getUser() {
        return this.user;
    }

    public void setUser(AbstractUser user) {
        this.user = user;
    }

    public Byte getCurrentQuestion() {
        return this.currentQuestion;
    }

    public void setCurrentQuestion(Byte currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public Boolean getFinished() {
        return this.isFinished;
    }

    public void setFinished(Boolean finished) {
        this.isFinished = finished;
    }

    public Long getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Set<Question> getAnsweredQuestions() {
        return Collections.unmodifiableSet(this.answeredQuestions);
    }

    public void setAnsweredQuestions(Set<Question> answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }
}