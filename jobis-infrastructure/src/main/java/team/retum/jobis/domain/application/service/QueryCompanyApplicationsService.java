package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.persistence.enums.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import team.retum.jobis.domain.application.presentation.dto.response.AttachmentResponse;
import team.retum.jobis.domain.application.presentation.dto.response.CompanyQueryApplicationsResponse;
import team.retum.jobis.domain.application.presentation.dto.response.CompanyQueryApplicationsResponse.CompanyQueryApplicationResponse;
import team.retum.jobis.domain.company.persistence.Company;
import team.retum.jobis.domain.recruitment.persistence.Recruitment;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.student.persistence.Student;
import team.retum.jobis.domain.persistence.facade.UserFacade;
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
                                .profileImageUrl(application.getProfileImageUrl())
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
