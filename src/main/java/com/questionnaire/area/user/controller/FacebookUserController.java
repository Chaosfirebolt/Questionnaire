package com.questionnaire.area.user.controller;

import com.questionnaire.area.user.entity.FacebookUser;
import com.questionnaire.area.user.service.interfaces.FacebookUserService;
import com.questionnaire.util.constants.Attribute;
import com.questionnaire.util.constants.Regex;
import com.questionnaire.util.constants.View;
import com.questionnaire.util.constants.error.RegistrationError;
import com.questionnaire.util.redirection.Redirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ChaosFire on 17.4.2017 г
 */
@Controller
@RequestMapping("/register/facebook")
public class FacebookUserController {

    private final FacebookUserService facebookUserService;
    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    @Autowired
    public FacebookUserController(FacebookUserService facebookUserService, Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebookUserService = facebookUserService;
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping("/set-username")
    public String finishFacebookRegistrationPage(Model model) {
        model.addAttribute(Attribute.VIEW.getName(), View.FACEBOOK_USERNAME.getAddress());
        return View.BASIC.getAddress();
    }

    @PostMapping("/set-username")
    public String finishFacebookRegistration(@RequestParam String facebookUsername, Model model) {
        Pattern usernamePattern = Pattern.compile(Regex.USERNAME);
        Matcher matcher = usernamePattern.matcher(facebookUsername);
        if (!matcher.find()) {
            model.addAttribute(Attribute.ERROR.getName(), RegistrationError.INVALID_USERNAME);
            model.addAttribute(Attribute.VIEW.getName(), View.FACEBOOK_USERNAME.getAddress());
            return View.BASIC.getAddress();
        }

        FacebookUser facebookUser = (FacebookUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = facebookUser.getEmail();
        this.facebookUserService.activateAccount(email, facebookUsername);

        final String logout = "/logout";
        return Redirection.redirect(logout);
    }

    @GetMapping("")
    public String registerOrLoginWithFacebook() throws Exception {
        Connection<Facebook> facebookConnection = this.connectionRepository.findPrimaryConnection(Facebook.class);
        User facebookUser = this.getFacebookUser(facebookConnection);

        this.facebookUserService.registerOrLogin(facebookUser);
        this.connectionRepository.removeConnection(facebookConnection.getKey());

        final String homePage = "/";
        return Redirection.redirect(homePage);
    }

    private User getFacebookUser(Connection<Facebook> facebookConnection) {
        ConnectionKey connectionKey = facebookConnection.getKey();
        String userKey = connectionKey.getProviderUserId();
        String[] fields = {"id", "email"};
        return this.facebook.fetchObject(userKey, User.class, fields);
    }
}