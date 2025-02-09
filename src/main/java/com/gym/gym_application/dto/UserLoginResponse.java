package com.gym.gym_application.dto;

import com.gym.gym_application.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {
	private Integer id;
	private String username;
	private String fullName;
	private Role role;
	private String token;
}