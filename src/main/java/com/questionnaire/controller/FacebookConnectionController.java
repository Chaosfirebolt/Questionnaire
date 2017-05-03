package com.questionnaire.controller;

import com.questionnaire.util.redirection.Redirection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;

/**
 * Created by ChaosFire on 17.4.2017 г
 */
@Controller
public class FacebookConnectionController extends ConnectController {

    private static final String FACEBOOK_REGISTER_PAGE = "/register/facebook";

    public FacebookConnectionController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        super(connectionFactoryLocator, connectionRepository);
    }

    @Override
    protected String connectedView(String providerId) {
        return Redirection.redirect(FACEBOOK_REGISTER_PAGE);
    }
}