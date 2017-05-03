package com.questionnaire.exception.account;

/**
 * Created by ChaosFire on 21.4.2017 г
 */
public class NotEnabledAccountException extends InvalidAccountException {

    public NotEnabledAccountException() {
        super();
    }

    public NotEnabledAccountException(String message) {
        super(message);
    }
}