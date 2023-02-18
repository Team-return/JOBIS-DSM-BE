package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.controller.dto.response.ApplicationDetailsResponse;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class QueryApplicationDetailsService {

    private final ApplicationRepository applicationRepository;

    @Transactional(readOnly = true)
    public ApplicationDetailsResponse execute(UUID applicationId) {
        return applicationRepository.queryApplicationDetails(applicationId);
    }
}
