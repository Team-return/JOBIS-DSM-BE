package com.example.jobis.domain.company.domain.repository;

import com.example.jobis.domain.company.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
