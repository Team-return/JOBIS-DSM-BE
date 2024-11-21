package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.domain.application.event.SingleApplicationStatusChangedEvent;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;

@RequiredArgsConstructor
@UseCase
public class RejectApplicationUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final CommandApplicationPort commandApplicationPort;
    private final PublishEventPort publishEventPort;

    public void execute(Long applicationId, String rejectReason) {
        Application application = queryApplicationPort.getByIdOrThrow(applicationId);

        commandApplicationPort.save(application.rejectApplication(rejectReason));
        publishEventPort.publishEvent(new SingleApplicationStatusChangedEvent(application, ApplicationStatus.REJECTED));
    }
}
