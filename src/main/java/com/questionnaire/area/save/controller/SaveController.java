package com.questionnaire.area.save.controller;

import com.questionnaire.area.save.dto.view.SaveView;
import com.questionnaire.area.save.service.interfaces.SaveLoadService;
import com.questionnaire.area.user.entity.AbstractUser;
import com.questionnaire.util.constants.Attribute;
import com.questionnaire.util.constants.View;
import com.questionnaire.util.redirection.Redirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by ChaosFire on 1.5.2017 г
 */
@Controller
@RequestMapping("/saves")
public class SaveController {

    private final SaveLoadService saveLoadService;

    @Autowired
    public SaveController(SaveLoadService saveLoadService) {
        this.saveLoadService = saveLoadService;
    }

    @GetMapping("")
    public String getAllSaves(Model model) {
        long userId = ((AbstractUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<SaveView> saveViewList = this.saveLoadService.getPlayerSaves(userId);
        model.addAttribute(Attribute.SAVES.getName(), saveViewList);

        model.addAttribute(Attribute.VIEW.getName(), View.SAVES.getAddress());
        return View.BASIC.getAddress();
    }

    @GetMapping("/load/{saveId}")
    public String loadGame(@PathVariable long saveId) {
        long gameId = this.saveLoadService.loadGame(saveId);
        final String gamePage = "/games/" + gameId + "/question";

        return Redirection.redirect(gamePage);
    }
}