package com.example.jobis.domain.application.domain.repository;

import com.example.jobis.domain.application.domain.Application;
import com.example.jobis.domain.application.domain.ApplicationAttachment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ApplicationRepository {

    private final ApplicationJpaRepository applicationJpaRepository;
    private final ApplicationAttachmentJpaRepository applicationAttachmentJpaRepository;

    public Application saveApplication(Application application) {
        return applicationJpaRepository.save(application);
    }

    public List<ApplicationAttachment> saveAllApplicationAttachment(List<ApplicationAttachment> applicationAttachments) {
        return applicationAttachmentJpaRepository.saveAll(applicationAttachments);
    }
}
