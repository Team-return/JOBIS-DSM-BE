package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.dto.response.AttachmentResponse;
import team.retum.jobis.domain.application.dto.response.CompanyQueryApplicationsResponse;
import team.retum.jobis.domain.application.dto.response.CompanyQueryApplicationsResponse.CompanyQueryApplicationResponse;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.student.model.SchoolNumber;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class CompanyQueryApplicationsUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SecurityPort securityPort;

    public CompanyQueryApplicationsResponse execute() {
        Company company = securityPort.getCurrentCompany();

        Recruitment recruitment = queryRecruitmentPort.queryRecentRecruitmentByCompanyId(company.getId())
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        return new CompanyQueryApplicationsResponse(
                queryApplicationPort.queryApplicationByConditions(
                                recruitment.getId(), null, ApplicationStatus.SEND, null
                        ).stream()
                        .map(application -> CompanyQueryApplicationResponse.builder()
                                .applicationId(application.getId())
                                .studentName(application.getName())
                                .studentNumber(
                                        SchoolNumber.processSchoolNumber(
                                                SchoolNumber.builder()
                                                        .grade(application.getGrade())
                                                        .classRoom(application.getClassNumber())
                                                        .number(application.getNumber())
                                                        .build()
                                        )
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
