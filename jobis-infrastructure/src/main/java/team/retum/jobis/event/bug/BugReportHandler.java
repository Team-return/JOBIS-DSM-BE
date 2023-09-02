package team.retum.jobis.event.bug;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import team.retum.jobis.event.bug.model.BugReportEvent;
import team.retum.jobis.thirdparty.webhook.WebhookUtil;

@RequiredArgsConstructor
@Component
public class BugReportHandler {

    private final WebhookUtil webhookUtil;

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onBugReportEvent(BugReportEvent event) {
        webhookUtil.sendBugReport(
                event.getBugReport(), event.getWriter()
        );
    }
}
