package team.retum.jobis.domain.user.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.user.persistence.User;

import java.util.Optional;

public interface UserJpaRepository extends CrudRepository<User, Long> {
    Optional<User> findByAccountId(String accountId);

    boolean existsByAccountId(String accountId);
}
