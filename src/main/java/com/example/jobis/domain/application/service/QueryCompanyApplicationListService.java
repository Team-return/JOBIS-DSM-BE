package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.controller.dto.response.QueryCompanyApplicationListResponse;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryCompanyApplicationListService {

    private final ApplicationRepository applicationRepository;
    private final CompanyFacade companyFacade;

    @Transactional(readOnly = true)
    public List<QueryCompanyApplicationListResponse> execute() {
        Company company = companyFacade.getCurrentCompany();
        return applicationRepository.queryCompanyApplicationList(company.getId());
    }
}
