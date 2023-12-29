package team.retum.jobis.domain.auth.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.auth.dto.TokenResponse;
import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.auth.model.RefreshToken;
import team.retum.jobis.domain.auth.spi.JwtPort;
import team.retum.jobis.domain.auth.spi.QueryRefreshTokenPort;

@RequiredArgsConstructor
@UseCase
public class TokenReissueUseCase {

    private final QueryRefreshTokenPort queryRefreshTokenPort;
    private final JwtPort jwtPort;

    public TokenResponse execute(String refresh, PlatformType platformType) {
        RefreshToken token = queryRefreshTokenPort.queryRefreshTokenByTokenAndPlatformType(refresh, platformType);
        TokenResponse newTokens = jwtPort.generateTokens(token.getUserId(), token.getAuthority(), platformType);

        return newTokens;
    }
}