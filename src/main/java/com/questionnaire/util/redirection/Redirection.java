package com.questionnaire.util.redirection;

/**
 * Created by ChaosFire on 17.4.2017 г
 */
public class Redirection {

    private static final String COMMAND = "redirect:";

    public static String redirect(String redirectTo) {
        return COMMAND + redirectTo;
    }
}