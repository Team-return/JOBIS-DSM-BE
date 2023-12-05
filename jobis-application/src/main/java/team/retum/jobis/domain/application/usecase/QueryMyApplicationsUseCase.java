package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.dto.response.AttachmentResponse;
import team.retum.jobis.domain.application.dto.response.QueryMyApplicationsResponse;
import team.retum.jobis.domain.application.dto.response.QueryMyApplicationsResponse.QueryMyApplicationResponse;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.student.model.Student;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryMyApplicationsUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final SecurityPort securityPort;

    public QueryMyApplicationsResponse execute(Long page) {
        Student student = securityPort.getCurrentStudent();

        return new QueryMyApplicationsResponse(
                queryApplicationPort.queryApplicationByConditions(
                                null, student.getId(), null, null, page).stream()
                        .map(application -> QueryMyApplicationResponse.builder()
                                .applicationId(application.getId())
                                .recruitmentId(application.getRecruitmentId())
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
