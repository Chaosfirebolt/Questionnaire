package com.questionnaire.controller.errorController;

import com.questionnaire.exception.question.NonExistentQuestionException;
import com.questionnaire.util.constants.Attribute;
import com.questionnaire.util.constants.View;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ChaosFire on 3.5.2017 г
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NonExistentQuestionException.class)
    public String noSuchQuestion(Model model) {
        model.addAttribute(Attribute.VIEW.getName(), View.ERROR.getAddress());
        String error = NonExistentQuestionException.class.getAnnotation(ResponseStatus.class).reason();
        model.addAttribute(Attribute.ERROR.getName(), error);
        return View.BASIC.getAddress();
    }
}