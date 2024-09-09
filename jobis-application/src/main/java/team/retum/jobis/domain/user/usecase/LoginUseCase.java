package team.retum.jobis.domain.user.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.auth.dto.response.TokenResponse;
import team.retum.jobis.domain.auth.spi.JwtPort;
import team.retum.jobis.domain.user.checker.UserChecker;
import team.retum.jobis.domain.user.dto.request.LoginRequest;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.UserPort;

@RequiredArgsConstructor
@UseCase
public class LoginUseCase {

    private final UserPort userPort;
    private final UserChecker userChecker;
    private final JwtPort jwtPort;

    public TokenResponse execute(LoginRequest request) {
        User user = (User) queryUserPort.queryUserByAccountId(request.accountId());
//            .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        userChecker.checkPasswordMatch(request.password(), user.getPassword());

        userPort.save(user.setToken(request.deviceToken()));

        return jwtPort.generateTokens(user.getId(), user.getAuthority(), request.platformType());
    }
}
