package com.gym.gym_application.repository;

import com.gym.gym_application.model.entity.UserTrainingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTrainingInfoRepository extends JpaRepository<UserTrainingInfo, Integer> {

}
