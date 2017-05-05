package com.questionnaire.util.constants;

/**
 * Created by ChaosFire on 18.4.2017 г.
 */
public enum Attribute {

    VIEW("view"),
    ERROR("error"),
    QUESTIONS("questions"),
    QUESTION("question"),
    USERNAME_MATCHES("usernameMatches"),
    SAVES("saves"),
    RANKING("ranking"),
    TIME("time"),
    TIMEOUT("timeout"),
    SEARCH("search");

    private String name;

    Attribute(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}