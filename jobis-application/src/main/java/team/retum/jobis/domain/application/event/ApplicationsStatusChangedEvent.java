package team.retum.jobis.domain.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.notification.model.Topic;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApplicationsStatusChangedEvent {

    private final List<Application> applications;
    private final ApplicationStatus status;
    private final List<Long> detailIds;
    private final Topic topic;
    private final List<String> deviceTokens;
}
