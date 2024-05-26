package com.example.workflow.controllers;

import com.example.workflow.models.Document;
import com.example.workflow.models.User;
import com.example.workflow.services.DocumentService;
import com.example.workflow.services.RecipientService;
import com.example.workflow.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
    public class DocumentsController {

    private final DocumentService documentService;
    private final UserService userService;
    private final RecipientService recipientService;

    @GetMapping("/")
    public String documents(@RequestParam(name = "title", required = false) String title, Model model, Principal principal){
        User user = documentService.getUserByPrincipal(principal);
        model.addAttribute("documents", documentService.listDocuments(title));
        model.addAttribute("user", documentService.getUserByPrincipal(principal));
        return "documents";
    }

    @GetMapping("/document/{id}")
    public String documentInfo(@PathVariable Long id, Model model, Principal principal){
        Document document = documentService.getDocumentById(id);
        model.addAttribute("user", documentService.getUserByPrincipal(principal));
        model.addAttribute("document", document);
        model.addAttribute("name", document.getUser());
        return "document-info";
    }

//    @PostMapping("/document/create")
//    public String createDocument(Document document, Principal principal) throws IOException {
//        documentService.saveDocuments(principal, document);
//        return "redirect:/my/documents";
//    }

    @PostMapping("/document/create")
    public String createDocument(@RequestParam("title") String title,
                                 @RequestParam("description") String description,
                                 @RequestParam("file") MultipartFile file,
                                 @RequestParam("userId") Long userId,
                                 Principal principal) throws IOException {
        Document document = new Document();
        document.setTitle(title);
        document.setDescription(description);
        documentService.saveDocumentWithFile(principal, document, file);

        User user = userService.getUserById(userId);
        recipientService.assignDocumentToUser(document, user);

        return "redirect:/my/documents";
    }

//    @PostMapping("/document/delete/{id}")
//    public String deleteDocument(@PathVariable Long id){
//        documentService.deleteDocument(id);
//        return "redirect:/";
//    }

    @GetMapping("/document/create/new")
    public String createNewDocument(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        List<User> users = userService.getAllUsers();
        model.addAttribute("user", user);
        model.addAttribute("users", users);
        model.addAttribute("recipients", List.of());
        return "create-document";
    }

    @GetMapping("/my/documents")
    public String userProducts(Principal principal, Model model) {
        User user = documentService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("documents", user.getDocuments());
        return "my-documents";
    }

    @PostMapping("/document/sign/{id}")
    public String signDoc(@PathVariable Long id){
        documentService.signDocument(id);
        return "redirect:/";
    }

    @PostMapping("/document/upload")
    public String uploadDocument(@RequestParam("file") MultipartFile file, Document document, Principal principal) throws IOException {
        documentService.saveDocumentWithFile(principal, document, file);
        return "redirect:/my/documents";
    }

    @GetMapping("/document/download/{id}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable Long id) throws IOException {
        Document document = documentService.getDocumentById(id);
        if (document == null) {
            return ResponseEntity.notFound().build();
        }

        Path filePath = Paths.get(document.getFile_path());
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filePath.getFileName().toString() + "\"")
                .body(resource);
    }
}
