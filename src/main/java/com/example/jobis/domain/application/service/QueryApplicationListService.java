package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.controller.dto.response.QueryApplicationListResponse;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class QueryApplicationListService {

    private final ApplicationRepository applicationRepository;

    @Transactional(readOnly = true)
    public List<QueryApplicationListResponse> execute(UUID companyId) {
        return applicationRepository.queryApplicationListByCompanyId(companyId);
    }
}
