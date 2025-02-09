package com.gym.gym_application.util;

import com.gym.gym_application.exception.exceptions.ConflictException;
import com.gym.gym_application.exception.exceptions.NotFoundException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthServiceUtil {

	public void validateUserNotExisting(Boolean exists) {
		if(Boolean.TRUE.equals(exists)) {
			throw new ConflictException("User with this username already exists");
		}
	}

	public void validateUserExisting(Boolean exists) {
		if(Boolean.FALSE.equals(exists)) {
			throw new NotFoundException("Cannot find user with this username");
		}
	}

	public void validatePassword(Boolean matches) {
		if(Boolean.FALSE.equals(matches)) {
			throw new ConflictException("Wrong password");
		}
	}
}
