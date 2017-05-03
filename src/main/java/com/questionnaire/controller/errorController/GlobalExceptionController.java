package com.questionnaire.controller.errorController;

import com.questionnaire.util.constants.Attribute;
import com.questionnaire.util.constants.View;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by ChaosFire on 3.5.2017 г
 */
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(Exception.class)
    public String handleException(Model model) {
        model.addAttribute(Attribute.VIEW.getName(), View.GLOBAL_ERROR.getAddress());
        return View.BASIC.getAddress();
    }
}