package com.questionnaire.util.constants.error;

/**
 * Created by ChaosFire on 15.4.2017 г
 */
public class RegistrationError {

    public static final String INVALID_USERNAME = "Username must contain only alphanumeric characters and be between 3 and 20 symbols long.";
    public static final String INVALID_EMAIL = "Email must be valid.";
    public static final String INVALID_PASSWORD = "Password must contain: min 8 and max 10 characters at least 1 uppercase alphabet, " +
            "1 lowercase alphabet, 1 number and 1 special character - [$ @ ! % * ?].";
}