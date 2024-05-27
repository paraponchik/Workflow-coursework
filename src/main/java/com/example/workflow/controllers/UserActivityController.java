package com.example.workflow.controllers;

import com.example.workflow.models.User;
import com.example.workflow.models.UserActivity;
import com.example.workflow.services.UserActivityService;
import com.example.workflow.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserActivityController {
    private final UserActivityService userActivityService;
    private final UserService userService;

    @GetMapping("/user/activities")
    public String getUserActivities(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        List<UserActivity> activities = userActivityService.getActivitiesByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("activities", activities);
        return "user-activities";
    }
}
