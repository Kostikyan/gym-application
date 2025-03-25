package com.gym.gym_application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.gym_application.config.TestSecurityConfig;
import com.gym.gym_application.config.jwt.JwtTokenUtil;
import com.gym.gym_application.dto.UserLoginRequest;
import com.gym.gym_application.dto.UserRegisterRequest;
import com.gym.gym_application.repository.UserRepository;
import com.gym.gym_application.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import(TestSecurityConfig.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtTokenUtil jwtTokenUtil;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_register_success() throws Exception {
        var request = new UserRegisterRequest("Test Test", "test@test.com", "testtest123");

        doNothing().when(authService).register(request);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void test_register_withInvalidValues() throws Exception {
        var invalidRequest = new UserRegisterRequest("", "test@test.com", "testtest123");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResponse().getContentAsString().contains("Invalid request content."))
                );
    }

    @Test
    void test_login_success() throws Exception {
        var validRequest = new UserLoginRequest("test@test.com", "testtest123");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void test_login_withInvalidValues() throws Exception {
        var validRequest = new UserLoginRequest("inv", "testtest123");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResponse().getContentAsString().contains("Invalid request content."))
                );
    }

}
