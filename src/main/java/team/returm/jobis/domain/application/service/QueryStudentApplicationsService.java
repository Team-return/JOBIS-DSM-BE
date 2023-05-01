package team.returm.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.presentation.dto.response.StudentQueryApplicationsResponse;
import team.returm.jobis.domain.application.presentation.dto.response.StudentQueryApplicationsResponse.StudentQueryApplicationResponse;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.ReadOnlyService;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryStudentApplicationsService {

    private final ApplicationRepository applicationRepository;
    private final UserFacade userFacade;

    public StudentQueryApplicationsResponse execute(Long page) {
        Student student = userFacade.getCurrentStudent();

        List<StudentQueryApplicationResponse> applications = applicationRepository.queryApplicationByConditions(
                        null, student.getId(), null, null, page - 1).stream()
                .map(application -> StudentQueryApplicationResponse.builder()
                        .applicationId(application.getId())
                        .company(application.getCompanyName())
                        .attachmentUrlList(application.getApplicationAttachmentUrl())
                        .applicationStatus(application.getApplicationStatus())
                        .build()
                ).toList();

        Integer count = applicationRepository.queryApplicationCountByConditions(
                null, student.getId(), null, null
        );

        return new StudentQueryApplicationsResponse(
                applications,
                count
        );
    }
}
