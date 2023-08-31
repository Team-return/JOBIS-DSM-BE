package team.retum.jobis.event.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.SendExceptionInfoPort;
import team.retum.jobis.event.exception.model.ExceptionEvent;

@RequiredArgsConstructor
@Component
public class ExceptionEventHandler {

    private final SendExceptionInfoPort sendExceptionInfoPort;

    @Async
    @EventListener
    public void onExceptionEvent(ExceptionEvent event) {
        sendExceptionInfoPort.sendExceptionInfo(event.getRequest(), event.getE());
    }
}
