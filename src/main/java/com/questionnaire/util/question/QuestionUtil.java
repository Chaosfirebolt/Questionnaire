package com.questionnaire.util.question;

/**
 * Created by ChaosFire on 4.5.2017 г
 */
public class QuestionUtil {

    public static byte calculateQuestionLevel(byte currentQuestion) {
        int remainder = currentQuestion % 10;
        byte questionLevel;
        if (remainder == 0) {
            questionLevel = (byte) (currentQuestion / 10);
        } else {
            questionLevel = (byte) ((currentQuestion / 10) + 1);
        }
        return questionLevel;
    }
}