package com.gym.gym_application.util;

import com.gym.gym_application.exception.exceptions.ConflictException;
import com.gym.gym_application.exception.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthServiceUtilTest {

    @Test
    void validateThatClassIsAnnotatedWithUtilityClass() {
        shouldBeFinalClass();
        shouldHavePrivateConstructor();
        allMethodsShouldBeStatic();
    }

    @Test
    void test_validateUserNotExisting_shouldDoNothing() {
        AuthServiceUtil.validateUserNotExisting(Boolean.FALSE);
        AuthServiceUtil.validateUserNotExisting(null);
    }

    @Test
    void test_validateUserNotExisting_shouldThrowException() {
        Assertions.assertThrows(
                ConflictException.class,
                () -> AuthServiceUtil.validateUserNotExisting(Boolean.TRUE),
                "User with this username already exists"
        );
    }

    @Test
    void test_validateUserExisting_shouldDoNothing() {
        AuthServiceUtil.validateUserExisting(Boolean.TRUE);
        AuthServiceUtil.validateUserExisting(null);
    }

    @Test
    void test_validateUserExisting_shouldThrowException() {
        Assertions.assertThrows(
                NotFoundException.class,
                () -> AuthServiceUtil.validateUserExisting(Boolean.FALSE),
                "Cannot find user with this username"
        );
    }

    @Test
    void test_validatePassword_shouldDoNothing() {
        AuthServiceUtil.validatePassword(Boolean.TRUE);
        AuthServiceUtil.validatePassword(null);
    }

    @Test
    void test_validatePassword_shouldThrowException() {
        Assertions.assertThrows(
                ConflictException.class,
                () -> AuthServiceUtil.validatePassword(Boolean.FALSE),
                "Wrong password"
        );
    }

    private void shouldBeFinalClass() {
        assertTrue(Modifier.isFinal(AuthServiceUtil.class.getModifiers()));
    }

    private void shouldHavePrivateConstructor() {
        Constructor<?>[] constructors = AuthServiceUtil.class.getDeclaredConstructors();
        assertEquals(1, constructors.length);
        Constructor<?> constructor = constructors[0];
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    private void allMethodsShouldBeStatic() {
        for (Method method : AuthServiceUtil.class.getDeclaredMethods()) {
            assertTrue(Modifier.isStatic(method.getModifiers()),
                    "Method " + method.getName() + " should be static");
        }
    }
}
