package com.questionnaire.interceptor.question;

import com.questionnaire.area.question.service.interfaces.QuestionInterceptorService;
import com.questionnaire.area.user.entity.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by ChaosFire on 25.4.2017 г
 */
@Component
public class QuestionValidationInterceptor extends HandlerInterceptorAdapter {

    private final QuestionInterceptorService questionInterceptorService;

    @Autowired
    public QuestionValidationInterceptor(QuestionInterceptorService questionInterceptorService) {
        this.questionInterceptorService = questionInterceptorService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasGodRole = false;
        String godRoleName = "ROLE_GOD";
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals(godRoleName)) {
                hasGodRole = true;
                break;
            }
        }

        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String pathVarName = "id";
        long questionId = Long.parseLong((String) pathVariables.get(pathVarName));

        long authorId = ((AbstractUser)authentication.getPrincipal()).getId();

        boolean isValidatorAuthor = this.questionInterceptorService.isValidatorAuthor(questionId, authorId);
        if (!hasGodRole && isValidatorAuthor) {
            final String homePage = "/";
            response.sendRedirect(homePage);
        }
        return true;
    }
}