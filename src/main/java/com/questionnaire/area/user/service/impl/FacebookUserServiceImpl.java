package com.questionnaire.area.user.service.impl;

import com.questionnaire.area.role.entity.Role;
import com.questionnaire.area.role.repository.RoleRepository;
import com.questionnaire.area.user.entity.FacebookUser;
import com.questionnaire.area.user.repository.FacebookUserRepository;
import com.questionnaire.area.user.service.interfaces.FacebookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;

/**
 * Created by ChaosFire on 15.4.2017 г
 */
@Service
public class FacebookUserServiceImpl extends UserServiceImpl implements FacebookUserService {

    private static final String FACEBOOK_USER_PASSWORD = "";

    @Autowired
    public FacebookUserServiceImpl(FacebookUserRepository facebookUserRepository, RoleRepository roleRepository) {
        super(facebookUserRepository, roleRepository);
    }

    @Override
    public void registerOrLogin(User facebookUser) {
        String email = facebookUser.getEmail();
        FacebookUserRepository facebookUserRepository = (FacebookUserRepository) super.getUserRepository();
        FacebookUser user = facebookUserRepository.findOneByEmail(email);
        if (user == null) {
            user = this.registerUser(email, facebookUserRepository);
        }
        this.loginUser(user);
    }

    private FacebookUser registerUser(String email, FacebookUserRepository facebookUserRepository) {
        final String DEFAULT_USERNAME = "";
        final boolean IS_ACCOUNT_NON_EXPIRED = true;
        final boolean IS_ACCOUNT_NON_LOCKED = true;
        final boolean IS_CREDENTIALS_NON_EXPIRED = true;
        final boolean IS_ENABLED = false;
        FacebookUser facebookUser = new FacebookUser(DEFAULT_USERNAME, email, FACEBOOK_USER_PASSWORD, IS_ACCOUNT_NON_EXPIRED,
                IS_ACCOUNT_NON_LOCKED, IS_CREDENTIALS_NON_EXPIRED, IS_ENABLED);

        Role defaultRole = super.getDefaultUserRole();
        facebookUser.addRole(defaultRole);

        facebookUser = facebookUserRepository.saveAndFlush(facebookUser);
        return facebookUser;
    }

    private void loginUser(FacebookUser facebookUser) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(facebookUser, null,
                facebookUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void activateAccount(String email, String username) {
        FacebookUserRepository facebookUserRepository = (FacebookUserRepository) super.getUserRepository();
        FacebookUser user = facebookUserRepository.findOneByEmail(email);
        user.setUsername(username);
        final boolean IS_ENABLED = true;
        user.setEnabled(IS_ENABLED);
        facebookUserRepository.saveAndFlush(user);
    }

    @Override
    public boolean isUserActive(FacebookUser facebookUser) {
        boolean isEnabled = true;
        if (facebookUser.getUsername().isEmpty() && !facebookUser.isEnabled()) {
            isEnabled = false;
        }
        return isEnabled;
    }
}