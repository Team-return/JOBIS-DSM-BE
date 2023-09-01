package team.retum.jobis.event.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import team.retum.jobis.event.exception.model.ExceptionEvent;
import team.retum.jobis.thirdparty.webhook.WebhookUtil;

@RequiredArgsConstructor
@Component
public class ExceptionEventHandler {

    private final WebhookUtil webhookUtil;

    @Async("asyncTaskExecutor")
    @EventListener
    public void onExceptionEvent(ExceptionEvent event) {
        webhookUtil.sendExceptionInfo(event.getRequest(), event.getE());
    }
}
