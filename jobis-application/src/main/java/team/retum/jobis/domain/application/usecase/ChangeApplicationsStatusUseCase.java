package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.domain.application.event.ApplicationsStatusChangedEvent;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.QueryUserPort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@UseCase
public class ChangeApplicationsStatusUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final CommandApplicationPort commandApplicationPort;
    private final PublishEventPort publishEventPort;
    private final QueryUserPort queryUserPort;

    public void execute(List<Long> applicationIds, ApplicationStatus status) {
        List<Application> applications = queryApplicationPort.getAllByIdInOrThrow(applicationIds);

        List<Long> detailIds = applications.stream()
            .map(Application::getId)
            .collect(Collectors.toList());

        List<String> deviceTokens = queryUserPort.getAllByIds(
                applications.stream()
                    .map(Application::getStudentId)
                    .toList()
            ).stream()
            .map(User::getToken)
            .collect(Collectors.toList());

        commandApplicationPort.updateApplicationStatus(status, applicationIds);
        publishEventPort.publishEvent(new ApplicationsStatusChangedEvent(
            applications,
            status,
            detailIds,
            Topic.APPLICATION,
            deviceTokens
        ));
    }
}
