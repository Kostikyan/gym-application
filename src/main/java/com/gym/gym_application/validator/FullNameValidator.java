package com.gym.gym_application.validator;

import com.gym.gym_application.annotation.ValidFullName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class FullNameValidator implements ConstraintValidator<ValidFullName, String> {

    private static final Pattern WORD_PATTERN = Pattern.compile("^[A-Z][a-zA-ZÀ-ÖØ-öø-ÿ'’\\-]+$");

    @Override
    public boolean isValid(String fullName, ConstraintValidatorContext context) {
        if (fullName == null || fullName.isBlank()) {
            return false;
        }

        String[] words = fullName.trim().split("\\s+");
        for (String word : words) {
            if (!WORD_PATTERN.matcher(word).matches()) {
                return false;
            }
        }

        return true;
    }
}
