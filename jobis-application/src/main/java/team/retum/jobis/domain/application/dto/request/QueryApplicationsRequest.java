package team.retum.jobis.domain.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.application.model.ApplicationStatus;

@Getter
@Builder
public class QueryApplicationsRequest {

    private final Long recruitmentId;

    private final Long studentId;

    private final ApplicationStatus applicationStatus;

    private final String studentName;

    private Long companyId;
}
