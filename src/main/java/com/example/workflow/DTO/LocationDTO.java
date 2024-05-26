package com.example.workflow.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.util.Set;

@Data
public class LocationDTO {
    private Long id;
    private String name;
    private double price;

    @JsonBackReference
    private Set<SubscriptionDTO> subscriptions;

    @JsonBackReference
    public Set<SubscriptionDTO> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<SubscriptionDTO> subscriptions) {
        this.subscriptions = subscriptions;
    }
}

