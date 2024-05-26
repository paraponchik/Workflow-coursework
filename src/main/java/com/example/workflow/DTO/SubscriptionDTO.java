package com.example.workflow.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.Set;

@Data
public class SubscriptionDTO {
    private Long id;
    private String name;
    private LocationDTO location;
    private Set<FavourDTO> favours;
    private double totalPrice;


    public double getTotalPrice() {
        double locationPrice = location != null ? location.getPrice() : 0;
        double favoursPrice = favours.stream().mapToDouble(FavourDTO::getPrice).sum();
        return locationPrice + favoursPrice;
    }

    @JsonManagedReference
    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    @JsonManagedReference
    public Set<FavourDTO> getFavours() {
        return favours;
    }

    public void setFavours(Set<FavourDTO> favours) {
        this.favours = favours;
    }
}
