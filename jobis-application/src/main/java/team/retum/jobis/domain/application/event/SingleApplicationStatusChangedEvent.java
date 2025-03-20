package team.retum.jobis.domain.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.notification.model.Topic;

@Getter
@AllArgsConstructor
public class SingleApplicationStatusChangedEvent {

    private final Application application;
    private final ApplicationStatus status;
    private final Long detailId;
    private final Topic topic;
    private final String deviceToken;
}
