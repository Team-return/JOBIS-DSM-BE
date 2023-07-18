package team.retum.jobis.domain.auth.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.auth.persistence.entity.RefreshTokenEntity;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, UUID> {
    Optional<RefreshTokenEntity> findByToken(String token);
}
