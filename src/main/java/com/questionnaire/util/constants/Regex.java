package com.questionnaire.util.constants;

/**
 * Created by ChaosFire on 15.4.2017 г
 */
public class Regex {

    public static final String USERNAME = "^[a-zA-Z]\\w{2,19}$";
    public static final String EMAIL = "^[a-zA-Z]\\w+@[a-zA-Z]{2,}(\\.[a-zA-Z]{2,})+$";
    public static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?])[A-Za-z\\d$@$!%*?]{8,10}$";
}