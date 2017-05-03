package com.questionnaire.area.question.dto.view;

import com.questionnaire.area.answer.dto.view.AnswerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ChaosFire on 22.4.2017 г
 */
public class QuestionView  extends QuestionBriefView {

    private List<AnswerView> possibleAnswers;

    public QuestionView() {
        super();
        this.setPossibleAnswers(new ArrayList<>());
    }

    public QuestionView(long id, String questionText, List<AnswerView> possibleAnswers) {
        super(id, questionText);
        this.setPossibleAnswers(possibleAnswers);
    }

    public List<AnswerView> getPossibleAnswers() {
        return Collections.unmodifiableList(this.possibleAnswers);
    }

    public void setPossibleAnswers(List<AnswerView> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}