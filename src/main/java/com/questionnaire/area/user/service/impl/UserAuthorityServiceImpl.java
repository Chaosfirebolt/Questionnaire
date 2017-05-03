package com.questionnaire.area.user.service.impl;

import com.questionnaire.area.role.entity.Role;
import com.questionnaire.area.role.repository.RoleRepository;
import com.questionnaire.area.user.entity.AbstractUser;
import com.questionnaire.area.user.repository.UserRepository;
import com.questionnaire.area.user.service.interfaces.UserAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ChaosFire on 28.4.2017 г
 */
@Service
public class UserAuthorityServiceImpl extends UserServiceImpl implements UserAuthorityService {

    private static final String ROLE_PREFIX = "ROLE_";

    @Autowired
    public UserAuthorityServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        super(userRepository, roleRepository);
    }

    @Override
    public void addRole(String username, String newRole) {
        String roleName = ROLE_PREFIX + newRole.toUpperCase();
        UserRepository userRepository = super.getUserRepository();
        AbstractUser user = userRepository.findOneByUsername(username);
        Role role = super.getRoleRepository().findByAuthority(roleName);
        user.addRole(role);
        userRepository.saveAndFlush(user);

    }

    @Override
    public void removeRole(String username, String roleToRemove) {
        String roleName = ROLE_PREFIX + roleToRemove.toUpperCase();
        final String DEFAULT_ROLE = "ROLE_USER";
        if (roleName.equals(DEFAULT_ROLE)) {
            return;
        }

        UserRepository userRepository = super.getUserRepository();
        AbstractUser user = userRepository.findOneByUsername(username);
        Role role = super.getRoleRepository().findByAuthority(roleName);
        user.removeRole(role);
        userRepository.saveAndFlush(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> findMatchingUsername(String username) {
        return super.getUserRepository().findByUsernameMatch(username);
    }
}