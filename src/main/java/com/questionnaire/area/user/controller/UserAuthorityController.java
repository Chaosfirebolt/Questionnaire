package com.questionnaire.area.user.controller;

import com.questionnaire.area.user.service.interfaces.UserAuthorityService;
import com.questionnaire.util.constants.Attribute;
import com.questionnaire.util.constants.View;
import com.questionnaire.util.redirection.Redirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by ChaosFire on 28.4.2017 г
 */
@Controller
@RequestMapping("/authority")
public class UserAuthorityController {

    private static final String AUTHORITY_PAGE = "/authority";

    private final UserAuthorityService userAuthorityService;

    @Autowired
    public UserAuthorityController(UserAuthorityService userAuthorityService) {
        this.userAuthorityService = userAuthorityService;
    }

    @GetMapping("")
    public String getAuthorityPage(Model model) {
        model.addAttribute(Attribute.VIEW.getName(), View.AUTHORITY.getAddress());
        return View.BASIC.getAddress();
    }

    @PostMapping("")
    public String findUsersByNameMatch(Model model, @RequestParam String username, RedirectAttributes redirectAttributes) {
        List<String> usernameMatches = this.userAuthorityService.findMatchingUsername(username);
        redirectAttributes.addFlashAttribute(Attribute.USERNAME_MATCHES.getName(), usernameMatches);
        model.addAttribute(Attribute.VIEW.getName(), View.AUTHORITY.getAddress());
        return Redirection.redirect(AUTHORITY_PAGE);
    }

    @GetMapping("/{username}/add")
    public String addRolePage(Model model) {
        return this.returnAuthorityInputPage(model);
    }

    @PostMapping("/{username}/add")
    public String addRoleToUserAuthority(@PathVariable String username, @RequestParam String role) {
        this.userAuthorityService.addRole(username, role);
        return Redirection.redirect(AUTHORITY_PAGE);
    }

    @GetMapping("/{username}/remove")
    public String removeRolePage(Model model) {
        return this.returnAuthorityInputPage(model);
    }

    @PostMapping("/{username}/remove")
    public String removeRoleFromUserAuthority(@PathVariable String username, @RequestParam String role) {
        this.userAuthorityService.removeRole(username, role);
        return Redirection.redirect(AUTHORITY_PAGE);
    }

    private String returnAuthorityInputPage(Model model) {
        model.addAttribute(Attribute.VIEW.getName(), View.AUTHORITY_INPUT.getAddress());
        return View.BASIC.getAddress();
    }
}