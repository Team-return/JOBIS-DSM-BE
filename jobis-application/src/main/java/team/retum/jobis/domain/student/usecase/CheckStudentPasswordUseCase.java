package team.retum.jobis.domain.student.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.user.exception.UserNotFoundException;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.QueryUserPort;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.user.exception.InvalidPasswordException;

@RequiredArgsConstructor
@UseCase
public class CheckStudentPasswordUseCase {

    private final SecurityPort securityPort;
    private final QueryUserPort queryUserPort;

    public void execute(String password) {
        Long currentUserId = securityPort.getCurrentUserId();
        User user = queryUserPort.queryUserById(currentUserId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!securityPort.isPasswordMatch(password, user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }
    }
}
