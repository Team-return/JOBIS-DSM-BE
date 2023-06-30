package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.domain.repository.ApplicationRepository;
import team.retum.jobis.domain.application.presentation.dto.response.AttachmentResponse;
import team.retum.jobis.domain.application.presentation.dto.response.StudentQueryApplicationsResponse;
import team.retum.jobis.domain.application.presentation.dto.response.StudentQueryApplicationsResponse.StudentQueryApplicationResponse;
import team.retum.jobis.domain.student.domain.Student;
import team.retum.jobis.domain.user.facade.UserFacade;
import team.retum.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryStudentApplicationsService {

    private final ApplicationRepository applicationRepository;
    private final UserFacade userFacade;

    public StudentQueryApplicationsResponse execute() {
        Student student = userFacade.getCurrentStudent();

        return new StudentQueryApplicationsResponse(
                applicationRepository.queryApplicationByConditions(
                                null, student.getId(), null, null).stream()
                        .map(application -> StudentQueryApplicationResponse.builder()
                                .applicationId(application.getId())
                                .company(application.getCompanyName())
                                .attachments(
                                        application.getApplicationAttachments().stream()
                                                .map(AttachmentResponse::of)
                                                .toList()
                                )
                                .applicationStatus(application.getApplicationStatus())
                                .build()
                        ).toList()
        );
    }
}
