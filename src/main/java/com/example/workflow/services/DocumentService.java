package com.example.workflow.services;

import com.example.workflow.models.Recipient;
import com.example.workflow.models.User;
import com.example.workflow.repositories.DocumentRepo;
import com.example.workflow.repositories.RecipientRepo;
import com.example.workflow.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.workflow.models.Document;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepo documentRepo;
    private final UserRepo userRepo;
    private final RecipientRepo recipientRepo;

    public List<Document> listDocuments(String title) {
        if (title != null) {
            return documentRepo.findByTitle(title);
        }
        return documentRepo.findAll();
    }

//    public void saveDocuments(Principal principal, Document document) throws IOException {
//        document.setUser(getUserByPrincipal(principal));
//        log.info("Saving new Document. Title: {}; Author email: {}", document.getTitle(), document.getUser().getEmail());
//        documentRepo.save(document);
//    }


    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return new User();
        }
        return userRepo.findByEmail(principal.getName());
    }


    public void deleteDocument(Long id) {
        documentRepo.deleteById(id);
    }

    public Document getDocumentById(Long id) {
        return documentRepo.findById(id).orElse(null);
    }

    public void signDocument(Long id) {
        Document document = documentRepo.findById(id).orElse(null);
        if(document != null) {
            if(document.isSign()) {
                document.setSign(false);
                log.info("Sign document with id {}; title: {}", document.getId(), document.getTitle());
            } else{
                document.setSign(true);
            }
        }
        documentRepo.save(document);
    }

    public void saveDocumentWithFile(Principal principal, Document document, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "user-files/" + principal.getName();
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            document.setFile_path(filePath.toString());
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }

        document.setUser(getUserByPrincipal(principal));
        log.info("Saving new Document. Title: {}; Author email: {}", document.getTitle(), document.getUser().getEmail());
        documentRepo.save(document);
    }

    public List<Document> getDocumentsAssignedToUser(User user) {
        List<Recipient> recipients = recipientRepo.findByUser(user);
        return recipients.stream()
                .map(Recipient::getDocument)
                .collect(Collectors.toList());
    }

}
