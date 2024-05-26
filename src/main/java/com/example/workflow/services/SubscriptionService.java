package com.example.workflow.services;

import com.example.workflow.models.Subscription;
import com.example.workflow.repositories.SubscriptionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepo subscriptionRepo;

    public Subscription saveSubscription(Subscription subscription) {
        subscription.calculateTotalPrice();
        return subscriptionRepo.save(subscription);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepo.findAll();
    }

}
