package team.returm.jobis.domain.auth.domain.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import team.returm.jobis.domain.auth.domain.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByToken(String token);
}
