package com.questionnaire.util.constants;

/**
 * Created by ChaosFire on 17.4.2017 г.
 */
public enum View {

    BASIC("layout/base-layout"),
    REGISTER("fragment/register"),
    LOGIN("fragment/login"),
    FACEBOOK_USERNAME("fragment/facebook-set-username"),
    ADD_QUESTION("fragment/question/add-question"),
    NOT_VALIDATED_QUESTIONS("fragment/question/question-not-validated-all"),
    QUESTION_VALIDATE("fragment/question/question-to-validate"),
    ACTIONS("fragment/question/actions"),
    AUTHORITY("fragment/authority/authority-find-user"),
    AUTHORITY_INPUT ("fragment/authority/authority-input"),
    GAME("fragment/game/game"),
    GAME_CHOICE("fragment/game/choice"),
    SAVES("fragment/game/save/saves-all"),
    RANKS("fragment/game/ranking/ranking"),
    RANKS_AVG("fragment/game/ranking/ranking-avg-time"),
    GLOBAL_ERROR("fragment/error/global-error"),
    ERROR("fragment/error/error");

    private String address;

    View(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }
}