package com.questionnaire.area.user.service.interfaces;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by ChaosFire on 28.4.2017 г
 */
@PreAuthorize("hasRole('GOD')")
public interface UserAuthorityService {

    void addRole(String username, String newRole);
    void removeRole(String username, String roleToRemove);
    List<String> findMatchingUsername(String username);
}