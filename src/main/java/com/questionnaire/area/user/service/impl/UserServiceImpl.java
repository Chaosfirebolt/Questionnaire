package com.questionnaire.area.user.service.impl;

import com.questionnaire.area.role.entity.Role;
import com.questionnaire.area.role.repository.RoleRepository;
import com.questionnaire.area.user.entity.AbstractUser;
import com.questionnaire.area.user.repository.UserRepository;
import com.questionnaire.area.user.service.interfaces.UserService;

/**
 * Created by ChaosFire on 15.4.2017 г
 */
public abstract class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    protected UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean usernameExists(String username) {
        AbstractUser user = this.getUserRepository().findOneByUsername(username);
        return user != null;
    }

    @Override
    public boolean emailExists(String email) {
        AbstractUser user = this.getUserRepository().findOneByEmail(email);
        return user != null;
    }

    protected Role getDefaultUserRole() {
        final String DEFAULT_ROLE = "role_user";
        return this.roleRepository.findByAuthority(DEFAULT_ROLE);
    }

    protected UserRepository getUserRepository() {
        return this.userRepository;
    }

    protected RoleRepository getRoleRepository() {
        return this.roleRepository;
    }
}