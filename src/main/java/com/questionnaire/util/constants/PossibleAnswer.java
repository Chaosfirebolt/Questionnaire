package com.questionnaire.util.constants;

/**
 * Created by ChaosFire on 23.4.2017 г.
 */
public enum PossibleAnswer {

    RIGHT("rightAnswer"),
    FIRST_WRONG("firstWrong"),
    SECOND_WRONG("secondWrong"),
    THIRD_WRONG("thirdWrong");

    private String attribute;

    PossibleAnswer(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return this.attribute;
    }
}