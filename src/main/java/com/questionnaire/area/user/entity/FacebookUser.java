package com.questionnaire.area.user.entity;

import com.questionnaire.area.game.entity.Game;
import com.questionnaire.area.role.entity.Role;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

/**
 * Created by ChaosFire on 13.4.2017 г
 */
@Entity
@DiscriminatorValue("facebook")
public class FacebookUser extends AbstractUser {

    private static final String PROVIDER = "FACEBOOK";

    @Column(name = "provider")
    private String provider;

    public FacebookUser() {
        super();
        this.setProvider(PROVIDER);
    }

    public FacebookUser(String username, String email, String password, Boolean isAccountNonExpired, Boolean isAccountNonLocked,
                        Boolean isCredentialsNonExpired, Boolean isEnabled) {
        super(username, email, password, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
        this.setProvider(PROVIDER);
    }

    public FacebookUser(String username, String email, String password, Boolean isAccountNonExpired, Boolean isAccountNonLocked,
                        Boolean isCredentialsNonExpired, Boolean isEnabled,
                        Set<Role> authorities, Set<Game> games) {
        super(username, email, password, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled, authorities, games);
        this.setProvider(PROVIDER);
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}