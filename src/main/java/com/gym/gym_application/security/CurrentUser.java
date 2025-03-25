package com.gym.gym_application.security;

import com.gym.gym_application.model.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private final transient User user;

    public CurrentUser(User user) {
        super(user.getUsername(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

}
