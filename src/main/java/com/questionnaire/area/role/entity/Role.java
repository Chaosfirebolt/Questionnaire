package com.questionnaire.area.role.entity;

import com.questionnaire.area.user.entity.AbstractUser;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ChaosFire on 13.4.2017 г
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable, GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "authority", unique = true, nullable = false)
    private String authority;
    @ManyToMany(mappedBy = "authorities")
    private Set<AbstractUser> usersWithRole;

    public Role() {
        super();
        this.setUsersWithRole(new HashSet<>());
    }

    public Role(String authority) {
        this.setAuthority(authority);
        this.setUsersWithRole(new HashSet<>());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<AbstractUser> getUsersWithRole() {
        return Collections.unmodifiableSet(this.usersWithRole);
    }

    public void setUsersWithRole(Set<AbstractUser> usersWithRole) {
        this.usersWithRole = usersWithRole;
    }
}