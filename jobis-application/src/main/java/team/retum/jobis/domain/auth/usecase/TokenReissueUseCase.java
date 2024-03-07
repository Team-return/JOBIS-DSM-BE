package team.retum.jobis.domain.auth.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.dto.response.TokenResponse;
import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.auth.model.RefreshToken;
import team.retum.jobis.domain.auth.spi.JwtPort;
import team.retum.jobis.domain.auth.spi.QueryRefreshTokenPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.CommandUserPort;

@RequiredArgsConstructor
@UseCase
public class TokenReissueUseCase {

    private final QueryRefreshTokenPort queryRefreshTokenPort;
    private final JwtPort jwtPort;
    private final SecurityPort securityPort;
    private final CommandUserPort commandUserPort;

    public TokenResponse execute(String refresh, PlatformType platformType, String deviceToken) {
        RefreshToken token = queryRefreshTokenPort.queryRefreshTokenByTokenAndPlatformType(refresh, platformType);
        User user = securityPort.getCurrentUser();

        commandUserPort.saveUser(user.setToken(deviceToken));
        return jwtPort.generateTokens(token.getUserId(), token.getAuthority(), platformType);
    }
}