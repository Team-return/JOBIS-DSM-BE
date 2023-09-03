package team.retum.jobis.domain.auth.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.auth.dto.TokenResponse;
import team.retum.jobis.domain.auth.model.RefreshToken;
import team.retum.jobis.domain.auth.spi.CommandRefreshTokenPort;
import team.retum.jobis.domain.auth.spi.JwtPort;
import team.retum.jobis.domain.auth.spi.QueryRefreshTokenPort;

@RequiredArgsConstructor
@UseCase
public class TokenReissueUseCase {

    private final QueryRefreshTokenPort queryRefreshTokenPort;
    private final CommandRefreshTokenPort commandRefreshTokenPort;
    private final JwtPort jwtPort;

    public TokenResponse execute(String refresh) {
        RefreshToken token = queryRefreshTokenPort.queryRefreshTokenByToken(refresh);
        TokenResponse newTokens = jwtPort.generateTokens(token.getId(), token.getAuthority());

        commandRefreshTokenPort.saveRefreshToken(
                token.update(newTokens.getRefreshToken(), jwtPort.getRefreshExp())
        );

        return newTokens;
    }
}