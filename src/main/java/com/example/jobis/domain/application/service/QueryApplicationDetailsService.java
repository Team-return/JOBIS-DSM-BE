package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.controller.dto.response.QueryApplicationDetailsResponse;
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
    public QueryApplicationDetailsResponse execute(UUID applicationId) {
        return new QueryApplicationDetailsResponse(applicationRepository.queryApplicationDetailsById(applicationId));
    }
}
