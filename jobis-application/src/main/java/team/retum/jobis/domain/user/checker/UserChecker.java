package team.retum.jobis.domain.user.checker;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.user.exception.InvalidPasswordException;

@RequiredArgsConstructor
@Service
public class UserChecker {

    private final SecurityPort securityPort;

    public void checkPasswordMatch(String rawPassword, String encodedPassword) {
        if (!securityPort.isPasswordMatch(rawPassword, encodedPassword)) {
            throw InvalidPasswordException.EXCEPTION;
        }
    }
}
