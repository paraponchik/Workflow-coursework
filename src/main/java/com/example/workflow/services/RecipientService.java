package com.example.workflow.services;

import com.example.workflow.models.Document;
import com.example.workflow.models.Recipient;
import com.example.workflow.models.User;
import com.example.workflow.repositories.RecipientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipientService {

    private final RecipientRepo recipientRepo;

    public List<Recipient> getRecipientsByUserId(Long userId) {
        return recipientRepo.findByUserId(userId);
    }

    public List<Recipient> getRecipientsByDocumentId(Long documentId) {
        return recipientRepo.findByDocumentId(documentId);
    }

    public void assignDocumentToUser(Document document, User user) {
        Recipient recipient = new Recipient();
        recipient.setDocument(document);
        recipient.setUser(user);
        recipientRepo.save(recipient);
    }

    public List<Recipient> getRecipientsByDocument(Document document) {
        return recipientRepo.findByDocument(document);
    }

    public List<Recipient> getRecipientsByUser(User user) {
        return recipientRepo.findByUser(user);
    }

    public Recipient getRecipientById(Long id) {
        return recipientRepo.findById(id).orElse(null);
    }

    public void removeRecipient(Long id) {
        recipientRepo.deleteById(id);
    }

    public void assignDocumentToUser(Document document, User user, User assignedBy) {
        Recipient recipient = new Recipient();
        recipient.setDocument(document);
        recipient.setUser(user);
        recipient.setAssignedBy(assignedBy);
        recipientRepo.save(recipient);
    }
}
