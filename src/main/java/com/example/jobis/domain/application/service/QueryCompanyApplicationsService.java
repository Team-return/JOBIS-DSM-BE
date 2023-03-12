package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.presentation.dto.response.QueryCompanyApplicationsResponse;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import com.example.jobis.domain.application.presentation.dto.request.QueryApplicationsRequest;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.facade.RecruitFacade;
import com.example.jobis.domain.student.domain.Student;
import com.example.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryCompanyApplicationsService {

    private final ApplicationRepository applicationRepository;
    private final CompanyFacade companyFacade;
    private final RecruitFacade recruitFacade;

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
                        .applicationId(a.getId())
                        .studentName(a.getName())
                        .studentNumber(Student.processGcn(
                                a.getGrade(),
                                a.getClassNumber(),
                                a.getNumber())
                        )
                        .applicationAttachmentUrl(a.getApplicationAttachmentUrl())
                        .createdAt(a.getCreatedAt().toLocalDate())
                        .build())
                .toList();
    }
}
