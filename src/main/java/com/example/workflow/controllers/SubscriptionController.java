package com.example.workflow.controllers;

import com.example.workflow.DTO.FavourDTO;
import com.example.workflow.DTO.LocationDTO;
import com.example.workflow.DTO.SubscriptionDTO;
import com.example.workflow.models.Location;
import com.example.workflow.models.Favour;
import com.example.workflow.models.Subscription;
import com.example.workflow.models.User;
import com.example.workflow.services.LocationService;
import com.example.workflow.services.FavourService;
import com.example.workflow.services.SubscriptionService;
import com.example.workflow.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final LocationService locationService;
    private final FavourService favourService;
    private final UserService userService;

    @GetMapping("/subscriptions")
    public String getAllSubscriptions(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        List<SubscriptionDTO> subscriptionDTOs = subscriptionService.getAllSubscriptions().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        model.addAttribute("subscriptions", subscriptionDTOs);
        model.addAttribute("user", user);
        return "subscriptions";
    }

    @GetMapping("/subscription/create")
    public String createSubscriptionForm(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        List<LocationDTO> locationDTOs = locationService.getAllLocations().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        List<FavourDTO> favourDTOs = favourService.getAllFavours().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        model.addAttribute("locations", locationDTOs);
        model.addAttribute("favours", favourDTOs);
        model.addAttribute("user", user);
        return "subscription-create";
    }

    @PostMapping("/subscription/create")
    public String createSubscription(@RequestParam String name,
                                     @RequestParam Long locationId,
                                     @RequestParam Set<Long> favourIds,
                                     Principal principal) {
        Location location = locationService.getLocationById(locationId);
        Set<Favour> favours = favourIds.stream()
                .map(favourService::getFavourById)
                .collect(Collectors.toSet());

        Subscription subscription = new Subscription();
        subscription.setName(name);
        subscription.setLocation(location);
        subscription.setFavours(favours);
        subscriptionService.saveSubscription(subscription);

        return "redirect:/subscriptions";
    }

    private SubscriptionDTO convertToDto(Subscription subscription) {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setId(subscription.getId());
        subscriptionDTO.setName(subscription.getName());
        subscriptionDTO.setLocation(convertToDto(subscription.getLocation()));
        subscriptionDTO.setFavours(subscription.getFavours().stream()
                .map(this::convertToDto)
                .collect(Collectors.toSet()));
        return subscriptionDTO;
    }

    private LocationDTO convertToDto(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setPrice(location.getPrice());
        return locationDTO;
    }

    private FavourDTO convertToDto(Favour favour) {
        FavourDTO favourDTO = new FavourDTO();
        favourDTO.setId(favour.getId());
        favourDTO.setName(favour.getName());
        favourDTO.setPrice(favour.getPrice());
        return favourDTO;
    }
}
