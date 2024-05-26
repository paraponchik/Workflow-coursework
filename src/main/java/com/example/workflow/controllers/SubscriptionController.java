package com.example.workflow.controllers;

import com.example.workflow.models.Location;
import com.example.workflow.models.Favour;
import com.example.workflow.models.Subscription;
import com.example.workflow.models.User;
import com.example.workflow.services.LocationService;
import com.example.workflow.services.FavourService;
import com.example.workflow.services.SubscriptionService;
import com.example.workflow.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final LocationService locationService;
    private final FavourService favourService;
    private final UserService userService;

    @GetMapping("/subscriptions")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String getAllSubscriptions(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        model.addAttribute("subscriptions", subscriptions);
        model.addAttribute("user", user);
        return "subscriptions";
    }

    @GetMapping("/subscription/create")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String createSubscriptionForm(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        List<Location> locations = locationService.getAllLocations();
        List<Favour> favours = favourService.getAllFavours();
        model.addAttribute("locations", locations);
        model.addAttribute("favours", favours);
        model.addAttribute("user", user);
        return "subscription-create";
    }

    @PostMapping("/subscription/create")
    public String createSubscription(@RequestParam String name,
                                     @RequestParam Long locationId,
                                     @RequestParam Long favourId,
                                     Principal principal) {
        Location location = locationService.getLocationById(locationId);
        Favour favour = favourService.getFavourById(favourId);

        Subscription subscription = new Subscription();
        subscription.setName(name);
        subscription.setLocation(location);
        subscription.setFavour(favour);
        subscriptionService.saveSubscription(subscription);

        return "redirect:/subscriptions";
    }

    @GetMapping("/favours")
    public String getAllFavour(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        List<Favour> favours = favourService.getAllFavours();
        model.addAttribute("favours", favours);
        model.addAttribute("user", user);
        return "favours";
    }

    @GetMapping("/locations")
    public String getAllLocation(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("user", user);
        return "locations";
    }
}
