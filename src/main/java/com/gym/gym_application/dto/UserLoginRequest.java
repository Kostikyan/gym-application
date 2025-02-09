package com.gym.gym_application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserLoginRequest(
		@NotBlank(message = "Username is required.")
		@Size(min = 4, max = 30, message = "Username must be between 4 and 30 characters.")
		String username,

		@NotBlank(message = "Password is required.")
		@Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters.")
		String password
) {
}