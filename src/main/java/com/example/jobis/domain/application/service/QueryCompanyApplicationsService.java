package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.controller.dto.response.QueryCompanyApplicationsResponse;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import com.example.jobis.domain.application.domain.repository.vo.QueryApplicationsByConditionsVO;
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
public class QueryCompanyApplicationsService {

    private final ApplicationRepository applicationRepository;
    private final CompanyFacade companyFacade;
    private final RecruitFacade recruitFacade;

    @Transactional(readOnly = true)
    public List<QueryCompanyApplicationsResponse> execute() {
        Company company = companyFacade.getCurrentCompany();
        Recruitment recruitment = recruitFacade.getLatestRecruitByCompany(company);
        return applicationRepository.queryApplicationByConditions(QueryApplicationsRequest.builder()
                        .recruitmentId(recruitment.getId())
                        .studentId(null)
                        .neApplicationStatus(ApplicationStatus.REQUESTED)
                        .eqApplicationStatus(null)
                        .studentName(null)
                        .build()).stream()
                .map(a -> QueryCompanyApplicationsResponse.builder()
                        .applicationId(a.getApplicationId())
                        .studentName(a.getStudentName())
                        .studentNumber(a.getStudentNumber())
                        .applicationAttachmentUrl(a.getApplicationAttachmentUrl())
                        .createdAt(a.getCreatedAt().toLocalDate())
                        .build())
                .toList();
    }
}
