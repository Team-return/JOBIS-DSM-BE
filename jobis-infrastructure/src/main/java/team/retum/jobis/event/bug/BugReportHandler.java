package team.retum.jobis.event.bug;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import team.retum.jobis.domain.bug.spi.SendBugReportPort;
import team.retum.jobis.event.bug.model.BugReportEvent;

@RequiredArgsConstructor
@Component
public class BugReportHandler {

    private final SendBugReportPort sendBugReportPort;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onBugReportEvent(BugReportEvent event) {
        sendBugReportPort.sendBugReport(
                event.getBugReport(), event.getBugAttachments(), event.getWriter()
        );
    }
}
