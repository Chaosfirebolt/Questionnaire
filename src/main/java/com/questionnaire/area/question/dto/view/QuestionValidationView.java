package com.questionnaire.area.question.dto.view;

import com.questionnaire.area.answer.dto.view.AnswerValidationView;

import java.util.Collections;
import java.util.List;

/**
 * Created by ChaosFire on 24.4.2017 г
 */
public class QuestionValidationView extends QuestionBriefView {

    private List<AnswerValidationView> possibleAnswers;
    private byte level;
    private String referenceUrl;

    public QuestionValidationView() {
        super();
    }

    public QuestionValidationView(long id, String questionText, byte level, List<AnswerValidationView> possibleAnswers, String referenceUrl) {
        super(id, questionText);
        this.setPossibleAnswers(possibleAnswers);
        this.setLevel(level);
        this.setReferenceUrl(referenceUrl);
    }

    public List<AnswerValidationView> getPossibleAnswers() {
        return Collections.unmodifiableList(this.possibleAnswers);
    }

    public void setPossibleAnswers(List<AnswerValidationView> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public byte getLevel() {
        return this.level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public String getReferenceUrl() {
        return this.referenceUrl;
    }

    public void setReferenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
    }
}