package com.gym.gym_application.service;

import com.gym.gym_application.dto.UserLoginRequest;
import com.gym.gym_application.dto.UserLoginResponse;
import com.gym.gym_application.dto.UserRegisterRequest;

public interface AuthService {

	UserLoginResponse login(UserLoginRequest request);

	void register(UserRegisterRequest request);
}
