package com.questionnaire.area.user.service.interfaces;

import com.questionnaire.area.user.entity.FacebookUser;
import org.springframework.social.facebook.api.User;

/**
 * Created by ChaosFire on 15.4.2017 г
 */
public interface FacebookUserService extends UserService {

    void registerOrLogin(User facebookUser);
    void activateAccount(String email, String username);
    boolean isUserActive(FacebookUser facebookUser);
}