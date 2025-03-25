package com.gym.gym_application.mapper;

import com.gym.gym_application.dto.UserRegisterRequest;
import com.gym.gym_application.model.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void test_mapFromRegisterDto_success() {
        var request = new UserRegisterRequest("Test", "Test", "Test");
        var result = mapper.mapFromRegisterDto(request);

        assertAll(
                () -> assertNull(result.getId()),
                () -> assertNull(result.getGender()),
                () -> assertNull(result.getDateOfBirth()),
                () -> assertNull(result.getCreationTimestamp()),
                () -> assertNull(result.getUpdateTimestamp()),
                () -> assertEquals(0, result.getUserTrainingInfos().size()),
                () -> assertEquals(0, result.getUserNutritionInfos().size()),
                () -> assertEquals(0, result.getUserPhysicalInfos().size()),
                () -> assertEquals("Test", result.getFullName()),
                () -> assertEquals("Test", result.getUsername()),
                () -> assertEquals("Test", result.getPassword()),
                () -> assertEquals(Role.USER, result.getRole())
        );
    }

    @Test
    void test_mapFromRegisterDto_withNullRequest() {
        var result = mapper.mapFromRegisterDto(null);
        assertNull(result);
    }

}
