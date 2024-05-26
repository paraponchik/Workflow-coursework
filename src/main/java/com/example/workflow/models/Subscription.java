package com.example.workflow.models;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double totalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "favour_id")
    private Favour favour;

    public double calculateTotalPrice() {
        double total = location != null ? location.getPrice() : 0;
        if (favour != null) {
            total += favour.getPrice();
        }
        this.totalPrice = total;
        return total;
    }
}
