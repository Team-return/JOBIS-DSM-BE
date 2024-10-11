package team.retum.jobis.domain.user.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByAccountId(String accountId);

    boolean existsByAccountId(String accountId);

    List<UserEntity> findAllByIdIn(List<Long> ids);

    Optional<UserEntity> findByToken(String token);
}
