package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.controller.dto.response.QueryCompanyApplicationsResponse;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import com.example.jobis.domain.application.controller.dto.request.QueryApplicationsRequest;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.facade.RecruitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        QueryApplicationsRequest request =
                QueryApplicationsRequest.builder()
                .recruitmentId(recruitment.getId())
                .applicationStatus(ApplicationStatus.APPROVED)
                .build();

        return applicationRepository.queryApplicationByConditions(request).stream()
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
