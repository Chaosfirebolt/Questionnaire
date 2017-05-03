package com.questionnaire.area.question.controller;

import com.questionnaire.area.question.service.interfaces.QuestionGodService;
import com.questionnaire.area.user.entity.AbstractUser;
import com.questionnaire.util.constants.Attribute;
import com.questionnaire.util.constants.View;
import com.questionnaire.util.redirection.Redirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ChaosFire on 28.4.2017 г
 */
@Controller
@RequestMapping("/questions/actions")
public class QuestionsGodController {

    private static final String ACTIONS_PAGE = "/questions/actions";

    private final QuestionGodService questionGodService;

    @Autowired
    public QuestionsGodController(@Qualifier("god") QuestionGodService questionGodService) {
        this.questionGodService = questionGodService;
    }

    @GetMapping("")
    public String getActionsPage(Model model) {
        model.addAttribute(Attribute.VIEW.getName(), View.ACTIONS.getAddress());
        return View.BASIC.getAddress();
    }

    @GetMapping("/add-new")
    public String addNewQuestions() {
        String username = ((AbstractUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        this.questionGodService.addNewQuestions(username);
        return Redirection.redirect(ACTIONS_PAGE);
    }

    @GetMapping("/validate-own")
    public String validateOwnQuestions() {
        long id = ((AbstractUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        this.questionGodService.validateOwnQuestions(id);
        return Redirection.redirect(ACTIONS_PAGE);
    }
}