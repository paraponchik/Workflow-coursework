package com.example.workflow.services;

import com.example.workflow.models.User;
import com.example.workflow.models.UserActivity;
import com.example.workflow.repositories.UserActivityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserActivityService {
    private final UserActivityRepo userActivityRepo;

    public void logActivity(User user, String activity) {
        UserActivity userActivity = new UserActivity(user, activity);
        userActivityRepo.save(userActivity);
    }

    public List<UserActivity> getActivitiesByUser(User user) {
        return userActivityRepo.findByUser(user);
    }
}
