package com.questionnaire.exception.account;

/**
 * Created by ChaosFire on 21.4.2017 г
 */
public abstract class InvalidAccountException extends Exception {

    protected InvalidAccountException() {
        super();
    }

    protected InvalidAccountException(String message) {
        super(message);
    }
}