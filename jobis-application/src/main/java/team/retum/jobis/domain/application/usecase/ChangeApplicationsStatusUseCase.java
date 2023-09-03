package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.NotificationPublish;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.notification.model.Topic;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ChangeApplicationsStatusUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final CommandApplicationPort commandApplicationPort;

    @NotificationPublish(topic = Topic.APPLICATION_STATUS_CHANGED)
    public List<Application> execute(List<Long> applicationIds, ApplicationStatus status) {
        List<Application> applications = queryApplicationPort.queryApplicationsByIds(applicationIds);
        if (applicationIds.size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        commandApplicationPort.changeApplicationStatus(status, applicationIds);

        return applications;
    }
}
