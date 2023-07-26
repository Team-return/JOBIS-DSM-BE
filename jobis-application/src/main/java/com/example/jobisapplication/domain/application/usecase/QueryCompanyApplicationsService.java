package com.example.jobisapplication.domain.application.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import com.example.jobisapplication.domain.company.exception.CompanyNotFoundException;
import com.example.jobisapplication.domain.company.model.Company;
import com.example.jobisapplication.domain.company.spi.QueryCompanyPort;
import com.example.jobisapplication.domain.recruitment.model.Recruitment;
import com.example.jobisapplication.domain.recruitment.spi.QueryRecruitmentPort;
import com.example.jobisapplication.domain.student.model.Student;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import com.example.jobisapplication.domain.application.dto.response.AttachmentResponse;
import com.example.jobisapplication.domain.application.dto.response.CompanyQueryApplicationsResponse;
import com.example.jobisapplication.domain.application.dto.response.CompanyQueryApplicationsResponse.CompanyQueryApplicationResponse;
import com.example.jobisapplication.domain.recruitment.exception.RecruitmentNotFoundException;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryCompanyApplicationsService {

    private final QueryApplicationPort queryApplicationPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SecurityPort securityPort;
    private final QueryCompanyPort queryCompanyPort;

    public CompanyQueryApplicationsResponse execute() {
        Long currentUserId = securityPort.getCurrentUserId();
        Company company = queryCompanyPort.queryCompanyById(currentUserId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        Recruitment recruitment = queryRecruitmentPort.queryRecentRecruitmentByCompanyId(company.getId())
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        return new CompanyQueryApplicationsResponse(
                queryApplicationPort.queryApplicationByConditions(
                                recruitment.getId(), null, ApplicationStatus.APPROVED, null
                        ).stream()
                        .map(application -> CompanyQueryApplicationResponse.builder()
                                .applicationId(application.getId())
                                .studentName(application.getName())
                                .studentNumber(Student.processGcn(
                                        application.getGrade(),
                                        application.getClassNumber(),
                                        application.getNumber())
                                )
                                .profileImageUrl(application.getProfileImageUrl())
                                .attachments(
                                        application.getApplicationAttachmentEntities().stream()
                                                .map(AttachmentResponse::of).toList()
                                )
                                .createdAt(application.getCreatedAt().toLocalDate())
                                .build()
                        ).toList()
        );
    }
}
