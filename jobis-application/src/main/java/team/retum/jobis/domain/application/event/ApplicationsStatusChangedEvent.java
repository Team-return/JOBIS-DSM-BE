package team.retum.jobis.domain.application.event;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;

import java.util.List;

@Getter
@Builder
public class ApplicationsStatusChangedEvent {

    private final List<Application> applications;
    private final ApplicationStatus status;
}
