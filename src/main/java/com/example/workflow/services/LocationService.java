package com.example.workflow.services;

import com.example.workflow.models.Location;
import com.example.workflow.repositories.LocationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepo locationRepo;

    public List<Location> getAllLocations() {
        return locationRepo.findAll();
    }

    public void saveLocation(Location location) {
        locationRepo.save(location);
    }

    public Location getLocationById(Long id) {
        return locationRepo.findById(id).orElse(null);
    }

    public void deleteLocationById(Long id) {
        locationRepo.deleteById(id);
    }
}