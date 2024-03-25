package team.retum.jobis.domain.user.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByAccountId(String accountId);

    boolean existsByAccountId(String accountId);

    List<UserEntity> findAllByIdIn(List<Long> ids);
}
