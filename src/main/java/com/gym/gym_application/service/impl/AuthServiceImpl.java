package com.gym.gym_application.service.impl;

import com.gym.gym_application.config.jwt.JwtTokenUtil;
import com.gym.gym_application.dto.UserLoginRequest;
import com.gym.gym_application.dto.UserLoginResponse;
import com.gym.gym_application.dto.UserRegisterRequest;
import com.gym.gym_application.exception.exceptions.NotFoundException;
import com.gym.gym_application.mapper.UserMapper;
import com.gym.gym_application.repository.UserRepository;
import com.gym.gym_application.security.CurrentUser;
import com.gym.gym_application.service.AuthService;
import com.gym.gym_application.util.AuthServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;

	@Override
	public UserLoginResponse login(UserLoginRequest request) {
		AuthServiceUtil.validateUserExisting(
				userRepository.existsByUsername(request.username())
		);

		var user = userRepository.findByUsername(request.username())
				.orElseThrow(NotFoundException::new);

		var matches = passwordEncoder.matches(request.password(), user.getPassword());
		AuthServiceUtil.validatePassword(matches);

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.username(),
				request.password()
		));

		var token = jwtTokenUtil.generateToken(new CurrentUser(user));
		return new UserLoginResponse(user.getId(), user.getUsername(), user.getFullName(), user.getRole(), token);
	}

	@Override
	public void register(UserRegisterRequest request) {
		AuthServiceUtil.validateUserNotExisting(
				userRepository.existsByUsername(request.getUsername())
		);

		request.setPassword(passwordEncoder.encode(request.getPassword()));

		var user = userMapper.mapFromRegisterDto(request);
		userRepository.save(user);
	}
}
