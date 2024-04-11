package team.retum.jobis.domain.student.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.model.AuthCode;
import team.retum.jobis.domain.auth.spi.QueryAuthCodePort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.UserPort;

@RequiredArgsConstructor
@UseCase
public class UpdateStudentForgottenPasswordUseCase {

    private final UserPort userPort;
    private final QueryAuthCodePort queryAuthCodePort;
    private final SecurityPort securityPort;

    public void execute(String email, String password) {
        User user = userPort.getByAccountIdOrThrow(email);

        AuthCode authCode = queryAuthCodePort.queryAuthCodeByEmail(email);
        authCode.checkIsVerified();

        userPort.save(
            user.updatePassword(securityPort.encodePassword(password))
        );
    }
}
