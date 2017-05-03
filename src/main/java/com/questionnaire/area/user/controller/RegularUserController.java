package com.questionnaire.area.user.controller;

import com.questionnaire.area.user.dto.bind.UserRegistrationBind;
import com.questionnaire.area.user.service.interfaces.RegularUserService;
import com.questionnaire.util.constants.Attribute;
import com.questionnaire.util.constants.error.LoginError;
import com.questionnaire.util.constants.View;
import com.questionnaire.util.redirection.Redirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by ChaosFire on 17.4.2017 г
 */
@Controller
public class RegularUserController {

    private final RegularUserService regularUserService;

    @Autowired
    public RegularUserController(RegularUserService regularUserService) {
        this.regularUserService = regularUserService;
    }

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute UserRegistrationBind userBind, Model model) {
        model.addAttribute(Attribute.VIEW.getName(), View.REGISTER.getAddress());
        return View.BASIC.getAddress();
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute UserRegistrationBind userBind, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(Attribute.VIEW.getName(), View.REGISTER.getAddress());
            return View.BASIC.getAddress();
        }

        boolean usernameExists = this.regularUserService.usernameExists(userBind.getUsername());
        boolean emailExists = this.regularUserService.emailExists(userBind.getEmail());
        boolean shouldRejectValues = false;
        if (usernameExists) {
            bindingResult.rejectValue("username", "error.userBind", "Username already exists");
            shouldRejectValues = true;
        }
        if (emailExists) {
            bindingResult.rejectValue("email", "error.userBind", "Email already exists");
            shouldRejectValues = true;
        }
        if (shouldRejectValues) {
            model.addAttribute(Attribute.VIEW.getName(), View.REGISTER.getAddress());
            return View.BASIC.getAddress();
        }

        this.regularUserService.registerUser(userBind);
        final String loginPage = "/login";
        return Redirection.redirect(loginPage);
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute(Attribute.VIEW.getName(), View.LOGIN.getAddress());
        if (error != null) {
            model.addAttribute(Attribute.ERROR.getName(), LoginError.INVALID_CREDENTIALS);
        }
        return View.BASIC.getAddress();
    }
}