package team.retum.jobis.event;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.PublishEventPort;

@RequiredArgsConstructor
@Component
public class RabbitMqProducer implements PublishEventPort {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publishEvent(Object event) {
        rabbitTemplate.convertAndSend(
            "notification.exchange",
            "notification.routing.key",
            event
        );
    }
}
