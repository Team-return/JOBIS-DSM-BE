package team.retum.jobis.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.PublishEventPort;

@RequiredArgsConstructor
@Component
public class EventPublisher implements PublishEventPort {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publishEvent(Object event) {
        eventPublisher.publishEvent(event);
    }
}
