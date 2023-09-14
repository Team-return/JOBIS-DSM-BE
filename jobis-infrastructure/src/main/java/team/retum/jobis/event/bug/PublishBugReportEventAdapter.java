package team.retum.jobis.event.bug;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.domain.bug.spi.PublishBugReportEventPort;
import team.retum.jobis.event.bug.model.BugReportEvent;

@RequiredArgsConstructor
@Component
public class PublishBugReportEventAdapter implements PublishBugReportEventPort {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publishBugReport(BugReport bugReport, String writer) {
        eventPublisher.publishEvent(BugReportEvent.builder()
                .bugReport(bugReport)
                .writer(writer)
                .build());
    }
}
