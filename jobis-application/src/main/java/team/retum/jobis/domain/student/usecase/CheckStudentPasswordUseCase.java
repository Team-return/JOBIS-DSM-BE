package team.retum.jobis.domain.student.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.user.exception.InvalidPasswordException;
import team.retum.jobis.domain.user.model.User;

@RequiredArgsConstructor
@UseCase
public class CheckStudentPasswordUseCase {

    private final SecurityPort securityPort;

    public void execute(String password) {
        User user = securityPort.getCurrentUser();

        if (!securityPort.isPasswordMatch(password, user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }
    }
}
