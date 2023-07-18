package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import team.retum.jobis.domain.application.presentation.dto.response.AttachmentResponse;
import team.retum.jobis.domain.application.presentation.dto.response.StudentQueryApplicationsResponse;
import team.retum.jobis.domain.application.presentation.dto.response.StudentQueryApplicationsResponse.StudentQueryApplicationResponse;
import team.retum.jobis.domain.student.persistence.StudentEntity;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryStudentApplicationsService {

    private final ApplicationRepository applicationRepository;
    private final UserFacade userFacade;

    public StudentQueryApplicationsResponse execute() {
        StudentEntity studentEntity = userFacade.getCurrentStudent();

        return new StudentQueryApplicationsResponse(
                applicationRepository.queryApplicationByConditions(
                                null, studentEntity.getId(), null, null).stream()
                        .map(application -> StudentQueryApplicationResponse.builder()
                                .applicationId(application.getId())
                                .company(application.getCompanyName())
                                .attachments(
                                        application.getApplicationAttachmentEntities().stream()
                                                .map(AttachmentResponse::of)
                                                .toList()
                                )
                                .applicationStatus(application.getApplicationStatus())
                                .build()
                        ).toList()
        );
    }
}
