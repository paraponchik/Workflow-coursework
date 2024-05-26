package com.example.workflow.repositories;

import com.example.workflow.models.Favour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavourRepo extends JpaRepository<Favour, Long> {
}