package com.questionnaire.exception.account;

/**
 * Created by ChaosFire on 21.4.2017 г
 */
public class ExpiredCredentialsException extends InvalidAccountException {

    public ExpiredCredentialsException() {
        super();
    }

    public ExpiredCredentialsException(String message) {
        super(message);
    }
}