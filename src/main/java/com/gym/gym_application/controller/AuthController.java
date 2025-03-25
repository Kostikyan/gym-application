package com.gym.gym_application.controller;

import com.gym.gym_application.dto.UserLoginRequest;
import com.gym.gym_application.dto.UserLoginResponse;
import com.gym.gym_application.dto.UserRegisterRequest;
import com.gym.gym_application.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    void register(@RequestBody @Valid UserRegisterRequest request) {
        authService.register(request);
    }

    @PostMapping("/login")
    UserLoginResponse login(@RequestBody @Valid UserLoginRequest request) {
        return authService.login(request);
    }
}
