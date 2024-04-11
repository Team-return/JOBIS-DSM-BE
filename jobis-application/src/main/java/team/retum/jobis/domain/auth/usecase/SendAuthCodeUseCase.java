package team.retum.jobis.domain.auth.usecase;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SendEmailPort;
import team.retum.jobis.domain.auth.model.AuthCode;
import team.retum.jobis.domain.auth.model.AuthCodeType;
import team.retum.jobis.domain.auth.spi.CommandAuthCodePort;
import team.retum.jobis.domain.student.exception.StudentAlreadyExistsException;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.user.spi.QueryUserPort;

@RequiredArgsConstructor
@UseCase
public class SendAuthCodeUseCase {

    private final CommandAuthCodePort commandAuthCodePort;
    private final QueryUserPort queryUserPort;
    private final SendEmailPort sendEmailPort;

    public void execute(String email, AuthCodeType authCodeType) {
        checkAuthCodeSendable(authCodeType, email);

        AuthCode authCode = AuthCode.builder()
            .code(RandomStringUtils.randomNumeric(6))
            .ttl(300)
            .isVerified(false)
            .email(email)
            .build();
        commandAuthCodePort.saveAuthCode(authCode);

        sendEmailPort.sendMail(authCode.getCode(), authCode.getEmail());
    }

    private void checkAuthCodeSendable(AuthCodeType authCodeType, String email) {
        if (authCodeType == AuthCodeType.SIGN_UP) {
            if (queryUserPort.existsByAccountId(email)) {
                throw StudentAlreadyExistsException.EXCEPTION;
            }
        } else {
            if (!queryUserPort.existsByAccountId(email)) {
                throw StudentNotFoundException.EXCEPTION;
            }
        }
    }
}
