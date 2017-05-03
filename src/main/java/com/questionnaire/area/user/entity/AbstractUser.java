package com.questionnaire.area.user.entity;

import com.questionnaire.area.game.entity.Game;
import com.questionnaire.area.role.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by ChaosFire on 13.4.2017 г
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractUser implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username", unique = true, nullable = false, length = 20)
    private String username;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "is_account_non_expired", nullable = false)
    private Boolean isAccountNonExpired;
    @Column(name = "is_account_non_locked", nullable = false)
    private Boolean isAccountNonLocked;
    @Column(name = "is_credentials_non_expired", nullable = false)
    private Boolean isCredentialsNonExpired;
    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> authorities;
    @OneToMany(mappedBy = "user")
    private Set<Game> games;

    protected AbstractUser() {
        super();
        this.initCollections();
    }

    protected AbstractUser(String username, String email, String password, Boolean isAccountNonExpired, Boolean isAccountNonLocked,
                        Boolean isCredentialsNonExpired, Boolean isEnabled) {
        this.setData(username, email, password, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
        this.initCollections();
    }

    protected AbstractUser(String username, String email, String password, Boolean isAccountNonExpired, Boolean isAccountNonLocked,
                        Boolean isCredentialsNonExpired, Boolean isEnabled, Set<Role> authorities, Set<Game> games) {
        this.setData(username, email, password, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
        this.setAuthorities(authorities);
        this.setGames(games);
    }

    private void setData(String username, String email, String password, Boolean isAccountNonExpired, Boolean isAccountNonLocked,
                         Boolean isCredentialsNonExpired, Boolean isEnabled) {
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
        this.setAccountNonExpired(isAccountNonExpired);
        this.setAccountNonLocked(isAccountNonLocked);
        this.setCredentialsNonExpired(isCredentialsNonExpired);
        this.setEnabled(isEnabled);
    }

    private void initCollections() {
        this.setAuthorities(new HashSet<>());
        this.setGames(new HashSet<>());
    }

    public void addRole(Role role) {
        this.authorities.add(role);
    }

    public void removeRole(Role role) {
        this.authorities.remove(role);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.isAccountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.isAccountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.isCredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        this.isEnabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableSet(this.authorities);
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    public Set<Game> getGames() {
        return Collections.unmodifiableSet(this.games);
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}