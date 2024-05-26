package com.example.workflow.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.util.Set;

@Data
public class FavourDTO {
    private Long id;
    private String name;
    private double price;

    @JsonBackReference
    private Set<SubscriptionDTO> subscriptions;

    // Getters and Setters

    @JsonBackReference
    public Set<SubscriptionDTO> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<SubscriptionDTO> subscriptions) {
        this.subscriptions = subscriptions;
    }
}

