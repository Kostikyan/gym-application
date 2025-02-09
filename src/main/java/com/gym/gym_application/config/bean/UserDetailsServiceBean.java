package com.gym.gym_application.config.bean;

import com.gym.gym_application.exception.exceptions.NotFoundException;
import com.gym.gym_application.repository.UserRepository;
import com.gym.gym_application.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceBean {

	private final UserRepository userRepository;

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> {
			var user = userRepository.findByUsername(username).orElseThrow(() ->
					new NotFoundException("Cannot find user with username %s".formatted(username)));
			return new CurrentUser(user);
		};
	}

}
