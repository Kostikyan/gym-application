package com.gym.gym_application.exception;

import com.gym.gym_application.exception.exceptions.ConflictException;
import com.gym.gym_application.exception.exceptions.NotFoundException;
import com.gym.gym_application.exception.exceptions.UnauthorizedUserException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.gym.gym_application.exception.ExceptionMessage.exceptionMessage;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({
			NotFoundException.class
	})
	public ResponseEntity<Object> handleNotFoundExceptions(RuntimeException e, HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ExceptionMessage exceptionMessage = exceptionMessage(e, httpStatus.value(), request);
		return new ResponseEntity<>(exceptionMessage, httpStatus);
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({
			UnauthorizedUserException.class
	})
	public ResponseEntity<Object> handleUnauthorizedExceptions(RuntimeException e, HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
		ExceptionMessage exceptionMessage = exceptionMessage(e, httpStatus.value(), request);
		return new ResponseEntity<>(exceptionMessage, httpStatus);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({
			ConflictException.class
	})
	public ResponseEntity<Object> handleConflictExceptions(RuntimeException e, HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.CONFLICT;
		ExceptionMessage exceptionMessage = exceptionMessage(e, httpStatus.value(), request);
		return new ResponseEntity<>(exceptionMessage, httpStatus);
	}
}
