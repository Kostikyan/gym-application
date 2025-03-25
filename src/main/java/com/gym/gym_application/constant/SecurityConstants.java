package com.gym.gym_application.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityConstants {
    protected static final String LOGOUT_URL = "/logout";
    protected static final String[] PERMITTED_URIS = {
            "/auth/**"
    };
}
