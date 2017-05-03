package com.questionnaire.interceptor;

import com.questionnaire.area.user.entity.FacebookUser;
import com.questionnaire.exception.account.InvalidAccountException;
import com.questionnaire.util.validation.account.AccountValidation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ChaosFire on 21.4.2017 г
 */
@Component
public class DisabledFacebookUserInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object userObject = authentication.getPrincipal();
        if (userObject instanceof FacebookUser) {
            FacebookUser facebookUser = (FacebookUser) userObject;
            try {
                AccountValidation.check(facebookUser);
            } catch (InvalidAccountException exc) {
                final String facebookUsernameRegPath = "/register/facebook/set-username";
                response.sendRedirect(facebookUsernameRegPath);
            }
        }
        return true;
    }
}