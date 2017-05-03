package com.questionnaire.area.question.controller;

import com.questionnaire.area.answer.dto.bind.AnswerBind;
import com.questionnaire.area.question.dto.bind.QuestionBind;
import com.questionnaire.area.question.dto.view.QuestionBriefView;
import com.questionnaire.area.question.dto.view.QuestionValidationView;
import com.questionnaire.area.question.service.interfaces.QuestionCrudService;
import com.questionnaire.area.reference.dto.bind.AnswerReferenceBind;
import com.questionnaire.area.user.entity.AbstractUser;
import com.questionnaire.util.constants.Attribute;
import com.questionnaire.util.constants.View;
import com.questionnaire.util.redirection.Redirection;
import com.questionnaire.util.request.RequestBodyInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by ChaosFire on 23.4.2017 г
 */
@Controller
@RequestMapping("/questions")
public class QuestionsCrudController {

    private final QuestionCrudService questionCrudService;

    @Autowired
    public QuestionsCrudController(@Qualifier("crud") QuestionCrudService questionCrudService) {
        this.questionCrudService = questionCrudService;
    }

    @GetMapping("/add")
    public String getAddQuestionPage(Model model, @ModelAttribute QuestionBind questionBind, @ModelAttribute AnswerBind answerBind) {
        model.addAttribute(Attribute.VIEW.getName(), View.ADD_QUESTION.getAddress());
        return View.BASIC.getAddress();
    }

    @PostMapping("/add")
    public String addQuestion(Model model, @Valid @ModelAttribute QuestionBind questionBind, BindingResult bindingResult,
                              @RequestBody String body, @ModelAttribute AnswerReferenceBind referenceBind) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(Attribute.VIEW.getName(), View.ADD_QUESTION.getAddress());
            return View.BASIC.getAddress();
        }

        RequestBodyInterpreter interpreter = new RequestBodyInterpreter(body);
        List<AnswerBind> answerBindList = interpreter.getPossibleAnswers();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        boolean thereAreViolations = false;
        for (AnswerBind answerBind : answerBindList) {
            Set<ConstraintViolation<AnswerBind>> violations = validator.validate(answerBind);
            if (violations.size() > 0) {
                thereAreViolations = true;
                break;
            }
        }
        Set<ConstraintViolation<AnswerReferenceBind>> violations = validator.validate(referenceBind);
        if (violations.size() > 0) {
            thereAreViolations = true;
        }
        if (thereAreViolations) {
            model.addAttribute(Attribute.VIEW.getName(), View.ADD_QUESTION.getAddress());
            return View.BASIC.getAddress();
        }

        questionBind.setPossibleAnswers(answerBindList);
        questionBind.setAnswerReference(referenceBind);

        String authorName = ((AbstractUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        this.questionCrudService.addQuestion(questionBind, authorName);

        final String homePage = "/";
        return Redirection.redirect(homePage);
    }

    @GetMapping("/validate")
    public String getQuestionValidationPage(Model model) {
        long authorId = ((AbstractUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<QuestionBriefView> notValidatedQuestions = this.questionCrudService.getNotValidatedQuestions(authorId);
        model.addAttribute(Attribute.QUESTIONS.getName(), notValidatedQuestions);
        model.addAttribute(Attribute.VIEW.getName(), View.NOT_VALIDATED_QUESTIONS.getAddress());
        return View.BASIC.getAddress();
    }

    @GetMapping("/validate/{id}")
    public String getQuestionToValidate(@PathVariable long id, Model model) {
        QuestionValidationView question = this.questionCrudService.showQuestionToValidate(id);
        model.addAttribute(Attribute.QUESTION.getName(), question);
        model.addAttribute(Attribute.VIEW.getName(), View.QUESTION_VALIDATE.getAddress());
        return View.BASIC.getAddress();
    }

    @PostMapping("/validate/{id}")
    public String validateQuestion(@PathVariable long id) {
        this.questionCrudService.validateQuestion(id);
        final String validationPage = "/questions/validate";
        return Redirection.redirect(validationPage);
    }
}