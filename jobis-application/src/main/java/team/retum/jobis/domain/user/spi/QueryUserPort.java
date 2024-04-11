package team.retum.jobis.domain.user.spi;

import team.retum.jobis.domain.user.model.User;

import java.util.List;
import java.util.Optional;

public interface QueryUserPort {

    User getByIdOrThrow(Long userId);

    boolean existsByAccountId(String accountId);

    User getByAccountIdOrThrow(String accountId);

    List<User> getAllByIds(List<Long> userIds);
}
