package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.common.util.NumberUtil;
import team.retum.jobis.domain.application.dto.response.AttachmentResponse;
import team.retum.jobis.domain.application.dto.response.TeacherQueryApplicationsResponse;
import team.retum.jobis.domain.application.dto.response.TeacherQueryApplicationsResponse.TeacherQueryApplicationResponse;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.student.model.SchoolNumber;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryApplicationsUseCase {

    private final QueryApplicationPort applicationPersistenceAdapter;

    public TeacherQueryApplicationsResponse execute(ApplicationStatus applicationStatus, String studentName, Long recruitmentId, Long page) {
        return new TeacherQueryApplicationsResponse(
                applicationPersistenceAdapter.queryApplicationByConditions(
                                recruitmentId, null, applicationStatus, studentName, page
                        ).stream()
                        .map(application -> TeacherQueryApplicationResponse.builder()
                                .applicationId(application.getId())
                                .studentName(application.getName())
                                .studentGcn(
                                        SchoolNumber.processSchoolNumber(
                                                SchoolNumber.builder()
                                                        .grade(application.getGrade())
                                                        .classRoom(application.getClassNumber())
                                                        .number(application.getNumber())
                                                        .build()
                                        )
                                )
                                .companyName(application.getCompanyName())
                                .attachments(
                                        application.getApplicationAttachmentEntities().stream()
                                                .map(AttachmentResponse::of).toList()
                                )
                                .createdAt(application.getCreatedAt().toLocalDate())
                                .applicationStatus(application.getApplicationStatus())
                                .build()
                        ).toList()
        );
    }

    public TotalPageCountResponse getTotalPageCount(ApplicationStatus applicationStatus, String studentName) {
        return new TotalPageCountResponse(
                NumberUtil.getTotalPageCount(
                        applicationPersistenceAdapter.queryApplicationCountByCondition(applicationStatus, studentName), 11
                )
        );
    }
}
