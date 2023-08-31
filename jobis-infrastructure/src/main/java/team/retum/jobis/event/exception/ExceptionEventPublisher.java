package team.retum.jobis.event.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.PublishExceptionPort;
import team.retum.jobis.event.exception.model.ExceptionEvent;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
public class ExceptionEventPublisher implements PublishExceptionPort {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publishException(HttpServletRequest request, Exception e) {
        eventPublisher.publishEvent(ExceptionEvent.builder()
                .request(request)
                .e(e)
                .build());
    }
}
