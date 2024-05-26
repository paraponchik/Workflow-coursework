package com.example.workflow.services;

import com.example.workflow.models.Favour;
import com.example.workflow.repositories.FavourRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavourService {
    private final FavourRepo favourRepo;

    public List<Favour> getAllFavours() {
        return favourRepo.findAll();
    }

    public void saveFavour(Favour favour) {
        favourRepo.save(favour);
    }

    public Favour getFavourById(Long id) {
        return favourRepo.findById(id).orElse(null);
    }

    public void deleteFavourById(Long id) {
        favourRepo.deleteById(id);
    }
}