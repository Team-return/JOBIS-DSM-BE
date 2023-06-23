package team.retum.jobis.domain.auth.domain.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.auth.domain.RefreshToken;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByToken(String token);
}
