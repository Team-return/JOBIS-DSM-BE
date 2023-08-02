package team.retum.jobis.domain.application.usecase;

import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.student.model.Student;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.dto.response.AttachmentResponse;
import team.retum.jobis.domain.application.dto.response.TeacherQueryApplicationsResponse;
import team.retum.jobis.domain.application.dto.response.TeacherQueryApplicationsResponse.TeacherQueryApplicationResponse;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryApplicationsUseCase {

    private final QueryApplicationPort applicationPersistenceAdapter;

    public TeacherQueryApplicationsResponse execute(ApplicationStatus applicationStatus, String studentName, Long recruitmentId) {
        int totalPageCount = (int) Math.ceil(
                applicationPersistenceAdapter.queryApplicationCountByCondition(applicationStatus, studentName).doubleValue() / 11
        );
        return new TeacherQueryApplicationsResponse(
                applicationPersistenceAdapter.queryApplicationByConditions(
                                recruitmentId, null, applicationStatus, studentName).stream()
                        .map(application -> TeacherQueryApplicationResponse.builder()
                                .applicationId(application.getId())
                                .studentName(application.getName())
                                .studentGcn(Student.processGcn(
                                        application.getGrade(),
                                        application.getClassNumber(),
                                        application.getNumber())
                                )
                                .companyName(application.getCompanyName())
                                .attachments(
                                        application.getApplicationAttachmentEntities().stream()
                                                .map(AttachmentResponse::of).toList()
                                )
                                .createdAt(application.getCreatedAt().toLocalDate())
                                .applicationStatus(application.getApplicationStatus())
                                .build()
                        ).toList(), totalPageCount
        );
    }
}
