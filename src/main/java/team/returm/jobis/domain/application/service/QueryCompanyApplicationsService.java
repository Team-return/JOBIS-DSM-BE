package team.returm.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.presentation.dto.response.CompanyQueryApplicationsResponse;
import team.returm.jobis.domain.application.presentation.dto.response.CompanyQueryApplicationsResponse.CompanyQueryApplicationResponse;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.facade.RecruitFacade;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.ReadOnlyService;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryCompanyApplicationsService {

    private final ApplicationRepository applicationRepository;
    private final RecruitFacade recruitFacade;
    private final UserFacade userFacade;

    public CompanyQueryApplicationsResponse execute(Long page) {
        Company company = userFacade.getCurrentCompany();
        Recruitment recruitment = recruitFacade.getLatestRecruitByCompany(company);

        List<CompanyQueryApplicationResponse> applications = applicationRepository.queryApplicationByConditions(
                        recruitment.getId(), null, ApplicationStatus.APPROVED, null, page - 1).stream()
                .map(application -> CompanyQueryApplicationResponse.builder()
                        .applicationId(application.getId())
                        .studentName(application.getName())
                        .studentNumber(Student.processGcn(
                                application.getGrade(),
                                application.getClassNumber(),
                                application.getNumber())
                        )
                        .applicationAttachmentUrl(application.getApplicationAttachmentUrl())
                        .createdAt(application.getCreatedAt().toLocalDate())
                        .build()
                ).toList();

        Integer count = applicationRepository.queryApplicationCountByConditions(
                recruitment.getId(), null, ApplicationStatus.APPROVED, null
        );

        return new CompanyQueryApplicationsResponse(
                applications,
                count
        );
    }
}
