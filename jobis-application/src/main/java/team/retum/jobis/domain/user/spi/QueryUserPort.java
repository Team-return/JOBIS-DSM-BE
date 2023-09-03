package team.retum.jobis.domain.user.spi;

import team.retum.jobis.domain.user.model.User;

import java.util.List;
import java.util.Optional;

public interface QueryUserPort {
    Optional<User> queryUserById(Long userId);

    boolean existsUserByAccountId(String accountId);

    Optional<User> queryUserByAccountId(String accountId);

    List<User> queryUsersByIdIn(List<Long> userIds);
}
