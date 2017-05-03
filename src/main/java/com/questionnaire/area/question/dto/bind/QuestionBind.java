package com.questionnaire.area.question.dto.bind;

import com.questionnaire.area.answer.dto.bind.AnswerBind;
import com.questionnaire.area.question.dto.AbstractQuestionDto;
import com.questionnaire.area.reference.dto.bind.AnswerReferenceBind;
import com.questionnaire.util.constants.error.QuestionInputError;
import org.hibernate.validator.constraints.Range;

import java.util.Collections;
import java.util.List;

/**
 * Created by ChaosFire on 22.4.2017 г
 */
public class QuestionBind extends AbstractQuestionDto {

    private static final byte MIN_QUESTION_LEVEL = 1;
    private static final byte MAX_QUESTION_LEVEL = 10;

    @Range(min = MIN_QUESTION_LEVEL, max = MAX_QUESTION_LEVEL, message = QuestionInputError.QUESTION_LEVEL_ERROR)
    private byte level;
    private List<AnswerBind> possibleAnswers;
    private AnswerReferenceBind answerReference;

    public QuestionBind() {
        super();
    }

    public QuestionBind(String questionText, byte level, List<AnswerBind> possibleAnswers, AnswerReferenceBind answerReference) {
        super(questionText);
        this.setLevel(level);
        this.setPossibleAnswers(possibleAnswers);
        this.setAnswerReference(answerReference);
    }

    public byte getLevel() {
        return this.level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public List<AnswerBind> getPossibleAnswers() {
        return Collections.unmodifiableList(this.possibleAnswers);
    }

    public void setPossibleAnswers(List<AnswerBind> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public AnswerReferenceBind getAnswerReference() {
        return this.answerReference;
    }

    public void setAnswerReference(AnswerReferenceBind answerReference) {
        this.answerReference = answerReference;
    }
}