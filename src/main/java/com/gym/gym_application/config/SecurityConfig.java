package com.gym.gym_application.config;

import com.gym.gym_application.config.jwt.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static com.gym.gym_application.constant.SecurityConstants.LOGOUT_URL;
import static com.gym.gym_application.constant.SecurityConstants.PERMITTED_URIS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

	private static final List<String> ALL = List.of("*");

	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final AuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	@SneakyThrows
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
		httpSecurity
				.csrf(AbstractHttpConfigurer::disable)
				.logout(logout -> logout
						.logoutUrl(LOGOUT_URL)
						.logoutSuccessHandler((request, response, authentication) -> {
									SecurityContextHolder.clearContext();
									response.setStatus(HttpServletResponse.SC_OK);
								}
						)
				)
				.authorizeHttpRequests(auth -> auth.requestMatchers(PERMITTED_URIS)
						.permitAll()
						.anyRequest().authenticated())
				.exceptionHandling(exceptionHandler -> exceptionHandler.authenticationEntryPoint(authenticationEntryPoint))
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.cors(SecurityConfig::getCorsConfigurer);
		return httpSecurity.build();
	}

	private static void getCorsConfigurer(CorsConfigurer<HttpSecurity> cors) {
		cors.configurationSource(request -> {
			var configuration = new CorsConfiguration();
			configuration.setAllowedOrigins(ALL);
			configuration.setAllowedMethods(ALL);
			configuration.setAllowedHeaders(ALL);
			return configuration;
		});
	}
}