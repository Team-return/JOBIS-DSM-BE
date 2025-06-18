package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.application.dto.response.QueryRejectionReasonResponse;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationRejectionAttachment;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryRejectionReasonUseCase {

    private final QueryApplicationPort queryApplicationPort;

    public QueryRejectionReasonResponse execute(Long applicationId) {
        Application application = queryApplicationPort.getByIdAndApplicationStatusOrThrow(applicationId, ApplicationStatus.REJECTED);

        return new QueryRejectionReasonResponse(application.getRejectionReason(),
            application.getApplicationRejectionAttachments());
    }
}
