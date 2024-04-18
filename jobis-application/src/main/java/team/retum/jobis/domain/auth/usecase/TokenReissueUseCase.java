package team.retum.jobis.domain.auth.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.auth.dto.response.TokenResponse;
import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.auth.model.RefreshToken;
import team.retum.jobis.domain.auth.spi.JwtPort;
import team.retum.jobis.domain.auth.spi.QueryRefreshTokenPort;
import team.retum.jobis.domain.user.exception.UserNotFoundException;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.CommandUserPort;
import team.retum.jobis.domain.user.spi.QueryUserPort;

@RequiredArgsConstructor
@UseCase
public class TokenReissueUseCase {

    private final QueryRefreshTokenPort queryRefreshTokenPort;
    private final JwtPort jwtPort;
    private final QueryUserPort queryUserPort;
    private final CommandUserPort commandUserPort;

    public TokenResponse execute(String refresh, PlatformType platformType, String deviceToken) {
        RefreshToken token = queryRefreshTokenPort.getByTokenAndPlatformType(refresh, platformType);
        User user = queryUserPort.getByIdOrThrow(token.getUserId());

        commandUserPort.save(user.setToken(deviceToken));

        return jwtPort.generateTokens(token.getUserId(), token.getAuthority(), platformType);
    }
}
