package team.retum.jobis.domain.auth.persistence;

import com.example.jobisapplication.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.jobisapplication.domain.auth.model.RefreshToken;
import com.example.jobisapplication.domain.auth.spi.RefreshTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.auth.persistence.mapper.RefreshTokenMapper;
import team.retum.jobis.domain.auth.persistence.repository.RefreshTokenRepository;

@RequiredArgsConstructor
@Component
public class RefreshTokenPersistenceAdapter implements RefreshTokenPort {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public void saveRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.save(
                refreshTokenMapper.toEntity(refreshToken)
        );
    }

    @Override
    public RefreshToken queryRefreshTokenByToken(String token) {
        return refreshTokenMapper.toDomain(
                refreshTokenRepository.findByToken(token)
                        .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION)
        );
    }
}