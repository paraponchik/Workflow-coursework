package com.example.workflow.controllers;

import com.example.workflow.models.Document;
import com.example.workflow.models.Recipient;
import com.example.workflow.models.User;
import com.example.workflow.services.DocumentService;
import com.example.workflow.services.RecipientService;
import com.example.workflow.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RecipientController {

    private final DocumentService documentService;
    private final RecipientService recipientService;
    private final UserService userService;

//    @PostMapping("/document/assign")
//    public String assignDocumentToUser(@RequestParam("documentId") Long documentId,
//                                       @RequestParam("userId") Long userId) {
//        Document document = documentService.getDocumentById(documentId);
//        User user = userService.getUserById(userId);
//
//        if (document != null && user != null) {
//            recipientService.assignDocumentToUser(document, user);
//        }
//
//        return "redirect:/document/create/new";
//    }

    @PostMapping("/document/delete/{documentId}")
    public String deleteDocument(@PathVariable("documentId") Long documentId) {
        documentService.deleteDocument(documentId);
        return "redirect:/";
    }

    @PostMapping("/document/assign")
    public String assignDocumentToUser(@RequestParam("documentId") Long documentId,
                                       @RequestParam("userId") Long userId,
                                       Principal principal) {
        Document document = documentService.getDocumentById(documentId);
        User user = userService.getUserById(userId);
        User assignedBy = userService.getUserByPrincipal(principal); // Получаем пользователя из Principal

        if (document != null && user != null && assignedBy != null) {
            recipientService.assignDocumentToUser(document, user, assignedBy); // Передаем assignedBy в метод
        }

        return "redirect:/document/create/new";
    }



}
