package com.gym.gym_application.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class FullNameValidatorTest {

    private final FullNameValidator fullNameValidator;

    private FullNameValidatorTest() {
        this.fullNameValidator = new FullNameValidator();
    }

    @ParameterizedTest
    @MethodSource("validFullNames")
    void test_isValid_withValidValues(String fullName) {
        Assertions.assertTrue(fullNameValidator.isValid(fullName, null));
    }

    @ParameterizedTest
    @MethodSource("invalidFullNames")
    void test_isValid_withInvalidValues(String fullName) {
        Assertions.assertFalse(fullNameValidator.isValid(fullName, null));
    }

    private static Stream<String> validFullNames() {
        return Stream.of(
                "John Doe",
                "Anna-Marie Smith",
                "Emile Zola Test With "
        );
    }

    private static Stream<String> invalidFullNames() {
        return Stream.of(
                "",
                null,
                "d",
                "10",
                "  ",
                "{}"
        );
    }
}
