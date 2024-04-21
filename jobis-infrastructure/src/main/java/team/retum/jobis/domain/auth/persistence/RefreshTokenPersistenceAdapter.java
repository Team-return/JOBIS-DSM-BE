package team.retum.jobis.domain.auth.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.auth.exception.RefreshTokenNotFoundException;
import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.auth.model.RefreshToken;
import team.retum.jobis.domain.auth.persistence.mapper.RefreshTokenMapper;
import team.retum.jobis.domain.auth.persistence.repository.RefreshTokenRepository;
import team.retum.jobis.domain.auth.spi.RefreshTokenPort;

@RequiredArgsConstructor
@Component
public class RefreshTokenPersistenceAdapter implements RefreshTokenPort {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenRepository.save(
            refreshTokenMapper.toEntity(refreshToken)
        );
    }

    @Override
    public RefreshToken getByTokenAndPlatformType(String token, PlatformType platformType) {
        return refreshTokenMapper.toDomain(
            refreshTokenRepository.findByTokenAndPlatformType(token, platformType)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION)
        );
    }

    @Override
    public void delete(RefreshToken refreshToken) {
        refreshTokenRepository.delete(
            refreshTokenMapper.toEntity(refreshToken)
        );
    }
}
