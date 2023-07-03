package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.domain.enums.ApplicationStatus;
import team.retum.jobis.domain.application.domain.repository.ApplicationRepository;
import team.retum.jobis.domain.application.presentation.dto.response.AttachmentResponse;
import team.retum.jobis.domain.application.presentation.dto.response.CompanyQueryApplicationsResponse;
import team.retum.jobis.domain.application.presentation.dto.response.CompanyQueryApplicationsResponse.CompanyQueryApplicationResponse;
import team.retum.jobis.domain.company.domain.Company;
import team.retum.jobis.domain.recruitment.domain.Recruitment;
import team.retum.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.student.domain.Student;
import team.retum.jobis.domain.user.facade.UserFacade;
import team.retum.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryCompanyApplicationsService {

    private final ApplicationRepository applicationRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;

    public CompanyQueryApplicationsResponse execute() {
        Company company = userFacade.getCurrentCompany();
        Recruitment recruitment = recruitmentRepository.queryRecentRecruitmentByCompanyId(company.getId())
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        return new CompanyQueryApplicationsResponse(
                applicationRepository.queryApplicationByConditions(
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
                                .attachments(
                                        application.getApplicationAttachments().stream()
                                                .map(AttachmentResponse::of).toList()
                                )
                                .createdAt(application.getCreatedAt().toLocalDate())
                                .build()
                        ).toList()
        );
    }
}