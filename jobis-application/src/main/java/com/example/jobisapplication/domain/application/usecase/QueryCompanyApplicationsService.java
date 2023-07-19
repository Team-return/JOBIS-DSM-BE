package com.example.jobisapplication.domain.application.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import com.example.jobisapplication.domain.company.model.Company;
import com.example.jobisapplication.domain.recruitment.model.Recruitment;
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
    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;

    public CompanyQueryApplicationsResponse execute() {
        Company company = userFacade.getCurrentCompany();
        Recruitment recruitment = recruitmentRepository.queryRecentRecruitmentByCompanyId(company.getId())
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
