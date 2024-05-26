package com.example.workflow.repositories;

import com.example.workflow.models.Document;
import com.example.workflow.models.Recipient;
import com.example.workflow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipientRepo extends JpaRepository<Recipient, Long> {
    List<Recipient> findByDocument(Document document);
    List<Recipient> findByUser(User user);
    List<Recipient> findByUserId(Long userId);
    List<Recipient> findByDocumentId(Long documentId);
}
