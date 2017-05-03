package com.questionnaire.exception.account;

/**
 * Created by ChaosFire on 21.4.2017 г
 */
public class LockedAccountException extends InvalidAccountException {

    public LockedAccountException() {
        super();
    }

    public LockedAccountException(String message) {
        super(message);
    }
}