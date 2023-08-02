package team.retum.jobis.domain.auth.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SesPort;
import team.retum.jobis.domain.auth.dto.SendAuthCodeRequest;
import team.retum.jobis.domain.auth.model.AuthCode;
import team.retum.jobis.domain.auth.spi.CommandAuthCodePort;
import team.retum.jobis.domain.user.spi.QueryUserPort;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.auth.model.AuthCodeType;
import team.retum.jobis.domain.student.exception.StudentAlreadyExistsException;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.common.util.StringUtil;

@RequiredArgsConstructor
@UseCase
public class SendAuthCodeUseCase {

    private final CommandAuthCodePort commandAuthCodePort;
    private final QueryUserPort queryUserPort;
    private final SesPort sesPort;

    public void execute(SendAuthCodeRequest request) {
        if (request.getAuthCodeType() == AuthCodeType.SIGN_UP) {
            if (queryUserPort.existsUserByAccountId(request.getEmail())) {
                throw StudentAlreadyExistsException.EXCEPTION;
            }
        } else {
            if (!queryUserPort.existsUserByAccountId(request.getEmail())) {
                throw StudentNotFoundException.EXCEPTION;
            }
        }

        AuthCode authCode = AuthCode.builder()
                .code(StringUtil.generateRandomCode(6))
                .ttl(300)
                .isVerified(false)
                .email(request.getEmail())
                .build();
        commandAuthCodePort.saveAuthCode(authCode);

        sesPort.sendMail(authCode.getCode(), authCode.getEmail());
    }
}
