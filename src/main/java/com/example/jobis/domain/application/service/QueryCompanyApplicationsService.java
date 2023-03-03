package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.controller.dto.response.QueryCompanyApplicationListResponse;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.facade.RecruitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryCompanyApplicationListService {

    private final ApplicationRepository applicationRepository;
    private final CompanyFacade companyFacade;
    private final RecruitFacade recruitFacade;

    @Transactional(readOnly = true)
    public List<QueryCompanyApplicationListResponse> execute() {
        Company company = companyFacade.getCurrentCompany();
        Recruitment recruitment = recruitFacade.getLatestRecruitByCompany(company);
        return applicationRepository.queryApplicationByConditions(recruitment.getId(), null, ApplicationStatus.REQUESTED, null, Year.now().getValue(), null).stream()
                .map(a -> QueryCompanyApplicationListResponse.builder()
                        .applicationId(a.getApplicationId())
                        .studentName(a.getStudentName())
                        .studentNumber(a.getStudentNumber())
                        .applicationAttachmentUrl(a.getApplicationAttachmentUrl())
                        .createdAt(a.getCreatedAt())
                        .build())
                .toList();
    }
}
