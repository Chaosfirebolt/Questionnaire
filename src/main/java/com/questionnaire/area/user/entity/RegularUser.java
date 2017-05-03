package com.questionnaire.area.user.entity;

import com.questionnaire.area.game.entity.Game;
import com.questionnaire.area.role.entity.Role;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

/**
 * Created by ChaosFire on 13.4.2017 г
 */
@Entity
@DiscriminatorValue("regular")
public class RegularUser extends AbstractUser {

    public RegularUser() {
        super();
    }

    public RegularUser(String username, String email, String password, Boolean isAccountNonExpired,
                       Boolean isAccountNonLocked, Boolean isCredentialsNonExpired, Boolean isEnabled) {
        super(username, email, password, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
    }

    public RegularUser(String username, String email, String password, Boolean isAccountNonExpired,
                       Boolean isAccountNonLocked, Boolean isCredentialsNonExpired, Boolean isEnabled,
                       Set<Role> authorities, Set<Game> games) {
        super(username, email, password, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled, authorities, games);
    }
}