package team.retum.jobis.domain.auth.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.auth.persistence.entity.RefreshTokenEntity;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, String> {
    Optional<RefreshTokenEntity> findByTokenAndPlatformType(String token, PlatformType platformType);

    Optional<RefreshTokenEntity> findByUserIdAndPlatformType(Long userId, PlatformType platformType);
}
