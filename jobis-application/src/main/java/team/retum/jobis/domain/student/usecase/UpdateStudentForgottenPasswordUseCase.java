package team.retum.jobis.domain.student.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.model.AuthCode;
import team.retum.jobis.domain.auth.spi.QueryAuthCodePort;
import team.retum.jobis.domain.user.exception.UserNotFoundException;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.CommandUserPort;
import team.retum.jobis.domain.user.spi.QueryUserPort;

@RequiredArgsConstructor
@UseCase
public class UpdateStudentForgottenPasswordUseCase {

    private final QueryUserPort queryUserPort;
    private final QueryAuthCodePort queryAuthCodePort;
    private final SecurityPort securityPort;
    private final CommandUserPort commandUserPort;

    public void execute(String email, String password) {
        if (!queryUserPort.existsUserByAccountId(email)) {
            throw UserNotFoundException.EXCEPTION;
        }

        AuthCode authCodeEntity = queryAuthCodePort.queryAuthCodeByEmail(email);
        authCodeEntity.checkIsVerified();

        User userEntity = queryUserPort.queryUserByAccountId(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        commandUserPort.saveUser(
                userEntity.updatePassword(securityPort.encodePassword(password))
        );
    }
}
