package com.example.workflow.repositories;

import com.example.workflow.models.User;
import com.example.workflow.models.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserActivityRepo extends JpaRepository<UserActivity, Long> {
    List<UserActivity> findByUser(User user);
}
