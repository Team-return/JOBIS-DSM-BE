package team.retum.jobis.domain.auth.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.auth.persistence.RefreshToken;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByToken(String token);
}
