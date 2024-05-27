package com.example.workflow.models;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_activities")
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "activity", nullable = false)
    private String activity;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    public UserActivity() {
    }

    public UserActivity(User user, String activity) {
        this.user = user;
        this.activity = activity;
        this.timestamp = LocalDateTime.now();
    }
}
