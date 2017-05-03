package com.questionnaire.area.user.service.interfaces;

import com.questionnaire.area.user.dto.bind.UserRegistrationBind;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by ChaosFire on 15.4.2017 г
 */
public interface RegularUserService extends UserService, UserDetailsService {

    void registerUser(UserRegistrationBind userBind);
}