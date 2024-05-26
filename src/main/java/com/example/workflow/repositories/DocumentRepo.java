package com.example.workflow.repositories;

import com.example.workflow.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepo extends JpaRepository<Document, Long> {
    List<Document> findByTitle(String title);
}
