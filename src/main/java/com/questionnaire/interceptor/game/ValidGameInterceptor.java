package com.questionnaire.interceptor.game;

import com.questionnaire.area.game.service.interfaces.GameInterceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ChaosFire on 30.4.2017 г
 */
@Component
public class ValidGameInterceptor extends HandlerInterceptorAdapter {

    private final GameInterceptorService gameInterceptorService;

    @Autowired
    public ValidGameInterceptor(GameInterceptorService gameInterceptorService) {
        this.gameInterceptorService = gameInterceptorService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        String gameIdString = url.split("/")[2];
        try {
            long gameId = Long.parseLong(gameIdString);
            boolean isGameFinished = this.gameInterceptorService.checkIsGameFinished(gameId);
            if (isGameFinished) {
                final String homePage = "/";
                response.sendRedirect(homePage);
            }
        } catch (NumberFormatException exc) {
            return true;
        }
        return true;
    }
}