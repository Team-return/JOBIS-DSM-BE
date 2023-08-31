package team.retum.jobis.event.bug;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.bug.model.BugAttachment;
import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.domain.bug.spi.PublishBugReportPort;
import team.retum.jobis.event.bug.model.BugReportEvent;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BugReportPublisher implements PublishBugReportPort {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publishBugReport(BugReport bugReport, List<BugAttachment> bugAttachments, String writer) {
        eventPublisher.publishEvent(BugReportEvent.builder()
                .bugReport(bugReport)
                .bugAttachments(bugAttachments)
                .writer(writer)
                .build());
    }
}
