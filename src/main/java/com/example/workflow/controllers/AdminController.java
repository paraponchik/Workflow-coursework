package com.example.workflow.controllers;

import com.example.workflow.enums.Role;
import com.example.workflow.models.Favour;
import com.example.workflow.models.Location;
import com.example.workflow.models.User;
import com.example.workflow.services.FavourService;
import com.example.workflow.services.LocationService;
import com.example.workflow.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminController {

    private final FavourService favourService;
    private final LocationService locationService;
    private final UserService userService;

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", userService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id){
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{id}")
    public String userEdit(@PathVariable("id") Long userId, Model model, Principal principal) {
        User editUser = userService.getUserById(userId);
        model.addAttribute("editUser", editUser);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") Long userId, @RequestParam Map<String, String> form) {
        User user = userService.getUserById(userId);
        if (user != null) {
            userService.updateUser(user, form);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/favours")
    public String manageFavours(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("favours", favourService.getAllFavours());
        return "favours-admin";
    }

    @PostMapping("/admin/favour/create")
    public String createFavour(@RequestParam String name, @RequestParam double price, Principal principal, Model model) {
        Favour favour = new Favour();
        favour.setName(name);
        favour.setPrice(price);
        favourService.saveFavour(favour);
        return "redirect:/admin/favours";
    }

    @GetMapping("/admin/favour/edit/{id}")
    public String editFavour(@PathVariable Long id, Principal principal, Model model) {
        Favour favour = favourService.getFavourById(id);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("favour", favour);
        return "favour-edit";
    }

    @PostMapping("/admin/favour/edit")
    public String updateFavour(@RequestParam Long id, @RequestParam String name, @RequestParam double price) {
        Favour favour = favourService.getFavourById(id);
        favour.setName(name);
        favour.setPrice(price);
        favourService.saveFavour(favour);
        return "redirect:/admin/favours";
    }

    @PostMapping("/admin/favour/delete/{id}")
    public String deleteFavour(@PathVariable Long id) {
        favourService.deleteFavourById(id);
        return "redirect:/admin/favours";
    }

    @GetMapping("/admin/places")
    public String managePlaces(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("places", locationService.getAllLocations());
        return "location-admin";
    }

    @PostMapping("/admin/place/create")
    public String createPlace(@RequestParam String name, @RequestParam double price, Principal principal, Model model) {
        Location place = new Location();
        place.setName(name);
        place.setPrice(price);
        locationService.saveLocation(place);
        return "redirect:/admin/places";
    }

    @GetMapping("/admin/place/edit/{id}")
    public String editPlace(@PathVariable Long id, Principal principal, Model model) {
        Location place = locationService.getLocationById(id);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("place", place);
        return "place-edit";
    }

    @PostMapping("/admin/place/edit")
    public String updatePlace(@RequestParam Long id, @RequestParam String name, @RequestParam double price) {
        Location place = locationService.getLocationById(id);
        place.setName(name);
        place.setPrice(price);
        locationService.saveLocation(place);
        return "redirect:/admin/places";
    }

    @PostMapping("/admin/place/delete/{id}")
    public String deletePlace(@PathVariable Long id) {
        locationService.deleteLocationById(id);
        return "redirect:/admin/places";
    }

}
