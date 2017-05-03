package com.questionnaire.interceptor;

import com.questionnaire.area.user.entity.FacebookUser;
import com.questionnaire.area.user.service.interfaces.FacebookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ChaosFire on 21.4.2017 г
 */
@Component
public class FacebookUserRegistrationInterceptor extends HandlerInterceptorAdapter {

    private final FacebookUserService facebookUserService;

    @Autowired
    public FacebookUserRegistrationInterceptor(FacebookUserService facebookUserService) {
        this.facebookUserService = facebookUserService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Object userObject = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final String homePage = "/";
        if (userObject instanceof FacebookUser) {
            FacebookUser facebookUser = (FacebookUser) userObject;
            boolean isUserActive = this.facebookUserService.isUserActive(facebookUser);
            if (isUserActive) {
                response.sendRedirect(homePage);
            }
        } else {
            response.sendRedirect(homePage);
        }
    }
}