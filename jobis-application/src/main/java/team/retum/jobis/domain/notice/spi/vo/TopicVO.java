package team.retum.jobis.domain.notice.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.notification.model.Topic;

@Getter
@AllArgsConstructor
public class TopicVO {

    private final Topic topic;
    private final boolean isSubscribed;
}
