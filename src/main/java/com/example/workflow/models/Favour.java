package com.example.workflow.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Favour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "subscription_favours",
            joinColumns = @JoinColumn(name = "favour_id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id")
    )
    private Set<Subscription> subscriptions;
}

