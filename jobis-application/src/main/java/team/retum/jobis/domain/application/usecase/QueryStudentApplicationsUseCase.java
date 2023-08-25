package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.dto.response.AttachmentResponse;
import team.retum.jobis.domain.application.dto.response.StudentQueryApplicationsResponse;
import team.retum.jobis.domain.application.dto.response.StudentQueryApplicationsResponse.StudentQueryApplicationResponse;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.student.model.Student;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryStudentApplicationsUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final SecurityPort securityPort;

    public StudentQueryApplicationsResponse execute() {
        Student student = securityPort.getCurrentStudent();

        return new StudentQueryApplicationsResponse(
                queryApplicationPort.queryApplicationByConditions(
                                null, student.getId(), null, null).stream()
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
