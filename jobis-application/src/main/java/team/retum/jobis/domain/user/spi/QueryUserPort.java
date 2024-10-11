package team.retum.jobis.domain.user.spi;

import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.user.model.User;

import java.util.List;

public interface QueryUserPort {

    User getByIdOrThrow(Long userId);

    boolean existsByAccountId(String accountId);

    User getByAccountIdOrThrow(String accountId);

    List<User> getAllByIds(List<Long> userIds);

    User getByStudentId(Long studentId);

    List<String> getDeviceTokenByTopic(Topic topic);

    User getUserIdByDeviceToken(String deviceToken);
}
