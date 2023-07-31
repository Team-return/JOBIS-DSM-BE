package team.retum.jobis.domain.auth.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.auth.dto.TokenResponse;
import team.retum.jobis.domain.auth.model.RefreshToken;
import team.retum.jobis.domain.auth.spi.JwtPort;
import team.retum.jobis.domain.auth.spi.QueryRefreshTokenPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class TokenReissueService {

    private final QueryRefreshTokenPort queryRefreshTokenPort;
    private final JwtPort jwtPort;

    public TokenResponse execute(String refresh) {
        RefreshToken token = queryRefreshTokenPort.queryRefreshTokenByToken(refresh);
        TokenResponse newTokens = jwtPort.generateTokens(token.getId(), token.getAuthority());
        token.update(newTokens.getRefreshToken(), jwtPort.getRefreshExp());

        return newTokens;
    }
}