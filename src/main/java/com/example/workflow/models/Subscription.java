package com.example.workflow.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonManagedReference
    private Location location;

    @ManyToMany
    @JoinTable(
            name = "subscription_favours",
            joinColumns = @JoinColumn(name = "subscription_id"),
            inverseJoinColumns = @JoinColumn(name = "favour_id")
    )
    @JsonManagedReference
    private Set<Favour> favours;

    @Transient
    private double totalPrice;

    public double calculateTotalPrice() {
        double total = location != null ? location.getPrice() : 0;
        for (Favour favour : favours) {
            total += favour.getPrice();
        }
        this.totalPrice = total;
        return total;
    }
}
