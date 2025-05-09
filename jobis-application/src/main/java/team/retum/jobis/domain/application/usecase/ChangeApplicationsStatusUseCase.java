package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.domain.application.event.ApplicationsStatusChangedEvent;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ChangeApplicationsStatusUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final CommandApplicationPort commandApplicationPort;
    private final PublishEventPort eventPublisher;

    public void execute(List<Long> applicationIds, ApplicationStatus status) {
        List<Application> applications = queryApplicationPort.getAllByIdInOrThrow(applicationIds);

        commandApplicationPort.updateApplicationStatus(status, applicationIds);
        eventPublisher.publishEvent(new ApplicationsStatusChangedEvent(applications, status));
    }
}
