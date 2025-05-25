package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.domain.application.dto.request.RejectionAttachmentRequest;
import team.retum.jobis.domain.application.event.SingleApplicationStatusChangedEvent;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.model.ApplicationRejectionAttachment;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class RejectApplicationUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final CommandApplicationPort commandApplicationPort;
    private final PublishEventPort eventPublisher;

    public void execute(Long applicationId, String rejectReason, List<RejectionAttachmentRequest> rejectionAttachments) {
        Application application = queryApplicationPort.getByIdOrThrow(applicationId);

        commandApplicationPort.save(application.rejectApplication(rejectReason, ApplicationRejectionAttachment.from(rejectionAttachments)));
        eventPublisher.publishEvent(new SingleApplicationStatusChangedEvent(application, ApplicationStatus.REJECTED));
    }
}
