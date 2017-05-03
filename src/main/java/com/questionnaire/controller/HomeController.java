package com.questionnaire.controller;

import com.questionnaire.util.constants.View;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ChaosFire on 17.4.2017 г
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage() {
        return View.BASIC.getAddress();
    }
}