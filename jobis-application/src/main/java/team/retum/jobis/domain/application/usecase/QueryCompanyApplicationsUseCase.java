package team.retum.jobis.domain.application.usecase;

import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.student.model.Student;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.dto.response.AttachmentResponse;
import team.retum.jobis.domain.application.dto.response.CompanyQueryApplicationsResponse;
import team.retum.jobis.domain.application.dto.response.CompanyQueryApplicationsResponse.CompanyQueryApplicationResponse;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryCompanyApplicationsUseCase {

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
