package team.retum.jobis.common.spi;

import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.user.model.User;

import java.util.List;
import java.util.Map;

public interface PublishNotificationEventPort {

    void publishSingleNotificationEvent(Topic topic, Long detailId, User user);

    void publishGroupNotificationEvent(Topic topic, Map<Long, List<User>> idUsersMap);
}
