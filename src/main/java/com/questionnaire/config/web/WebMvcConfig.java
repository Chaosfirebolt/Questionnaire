package com.questionnaire.config.web;

import com.questionnaire.interceptor.DisabledFacebookUserInterceptor;
import com.questionnaire.interceptor.FacebookUserRegistrationInterceptor;
import com.questionnaire.interceptor.QuestionValidationInterceptor;
import com.questionnaire.interceptor.ValidGameInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ChaosFire on 21.4.2017 г
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private static final String FACEBOOK_REG_USERNAME_PATH = "/register/facebook/set-username";
    private static final String QUESTION_VALIDATION_PATH = "/questions/validate/*";
    private static final String GAME_PATH = "/games/**";

    private final FacebookUserRegistrationInterceptor facebookUserRegistrationInterceptor;
    private final DisabledFacebookUserInterceptor disabledFacebookUserInterceptor;
    private final QuestionValidationInterceptor questionValidationInterceptor;
    private final ValidGameInterceptor validGameInterceptor;

    @Autowired
    public WebMvcConfig(FacebookUserRegistrationInterceptor facebookUserRegistrationInterceptor,
                        DisabledFacebookUserInterceptor disabledFacebookUserInterceptor,
                        QuestionValidationInterceptor questionValidationInterceptor,
                        ValidGameInterceptor validGameInterceptor) {
        this.facebookUserRegistrationInterceptor = facebookUserRegistrationInterceptor;
        this.disabledFacebookUserInterceptor = disabledFacebookUserInterceptor;
        this.questionValidationInterceptor = questionValidationInterceptor;
        this.validGameInterceptor = validGameInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.facebookUserRegistrationInterceptor).addPathPatterns(FACEBOOK_REG_USERNAME_PATH);
        registry.addInterceptor(this.disabledFacebookUserInterceptor).excludePathPatterns(FACEBOOK_REG_USERNAME_PATH);
        registry.addInterceptor(this.questionValidationInterceptor).addPathPatterns(QUESTION_VALIDATION_PATH);
        registry.addInterceptor(this.validGameInterceptor).addPathPatterns(GAME_PATH);
    }
}