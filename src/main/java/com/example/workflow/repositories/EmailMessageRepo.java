package com.example.workflow.repositories;

import com.example.workflow.models.EmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailMessageRepo extends JpaRepository<EmailMessage, Long> {
}
