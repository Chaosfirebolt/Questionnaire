package com.questionnaire.area.user.service.interfaces;

/**
 * Created by ChaosFire on 15.4.2017 г
 */
public interface UserService {

    boolean usernameExists(String username);
    boolean emailExists(String email);
}