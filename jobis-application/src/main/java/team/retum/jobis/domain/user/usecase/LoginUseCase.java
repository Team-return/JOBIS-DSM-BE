package team.retum.jobis.domain.user.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.dto.TokenResponse;
import team.retum.jobis.domain.auth.spi.JwtPort;
import team.retum.jobis.domain.user.dto.LoginRequest;
import team.retum.jobis.domain.user.exception.UserNotFoundException;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.QueryUserPort;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.user.exception.InvalidPasswordException;

@RequiredArgsConstructor
@UseCase
public class LoginUseCase {

    private final QueryUserPort queryUserPort;
    private final SecurityPort securityPort;
    private final JwtPort jwtPort;

    public TokenResponse execute(LoginRequest request) {

        User user = queryUserPort.queryUserByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!securityPort.isPasswordMatch(request.getPassword(), user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        return jwtPort.generateTokens(user.getId(), user.getAuthority());
    }
}
