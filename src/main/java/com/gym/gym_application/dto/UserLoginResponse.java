package com.gym.gym_application.dto;

import com.gym.gym_application.model.enums.Role;

public record UserLoginResponse(
        Integer id,
        String username,
        String fullName,
        Role role,
        String token
) {
}
