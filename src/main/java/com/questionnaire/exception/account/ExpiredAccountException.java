package com.questionnaire.exception.account;

/**
 * Created by ChaosFire on 21.4.2017 г
 */
public class ExpiredAccountException extends InvalidAccountException {

    public ExpiredAccountException() {
        super();
    }

    public ExpiredAccountException(String message) {
        super(message);
    }
}