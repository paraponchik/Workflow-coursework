package com.example.workflow.services;

import com.example.workflow.enums.Role;
import com.example.workflow.models.User;
import com.example.workflow.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public boolean createUser(User user) {
        String email = user.getEmail();

         if (userRepository.findByEmail(email) != null) {
             return false;
         }
         user.setActive(true);
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         user.getRoles().add(Role.ROLE_ADMIN);
         log.info("Saving new user with email {}", email);
         userRepository.save(user);

        // Send email notification
        String subject = "Registration Successful";
        String body = "Dear " + user.getName() + ",\n\nThank you for registering at our service.\n\nBest regards,\nYour Company";
        emailService.sendEmail(user.getEmail(), subject, body);

         return true;
    }

    public List<User> list(){
        return userRepository.findAll();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null){
            if (user.isActive()) {
                user.setActive(false);
                log.info("Banning user with id {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unbanning user with id {}; email: {}", user.getId(), user.getEmail());
            }
        }
        userRepository.save(user);
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        String selectedRole = form.get("role");
        if (roles.contains(selectedRole)) {
            user.getRoles().add(Role.valueOf(selectedRole));
        }
        userRepository.save(user);
    }

    public void updateUser(User user, Map<String, String> form) {
        user.setName(form.get("name"));
        user.setSurname(form.get("surname"));
        user.setEmail(form.get("email"));
        changeUserRoles(user, form);
        userRepository.save(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
