package team.returm.jobis.domain.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.presentation.dto.request.QueryApplicationsRequest;
import team.returm.jobis.domain.application.presentation.dto.response.QueryCompanyApplicationsResponse;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.facade.RecruitFacade;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryCompanyApplicationsService {

    private final ApplicationRepository applicationRepository;
    private final RecruitFacade recruitFacade;
    private final UserFacade userFacade;

    public List<QueryCompanyApplicationsResponse> execute() {
        Company company = userFacade.getCurrentCompany();
        Recruitment recruitment = recruitFacade.getLatestRecruitByCompany(company);

        return applicationRepository.queryApplicationByConditions(
                recruitment.getId(), null, ApplicationStatus.APPROVED, null, null).stream()
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
