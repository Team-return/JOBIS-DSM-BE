package team.retum.jobis.domain.auth.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.auth.model.AuthCode;
import team.retum.jobis.domain.auth.spi.CommandAuthCodePort;
import team.retum.jobis.domain.auth.spi.QueryAuthCodePort;

@RequiredArgsConstructor
@UseCase
public class VerifyAuthCodeUseCase {

    private final CommandAuthCodePort commandAuthCodePort;
    private final QueryAuthCodePort queryAuthCodePort;

    public void execute(String email, String code) {
        AuthCode authCode = queryAuthCodePort.queryAuthCodeByEmail(email);
        authCode.verifyCode(code);

        commandAuthCodePort.saveAuthCode(
            authCode.verify()
        );
    }
}
