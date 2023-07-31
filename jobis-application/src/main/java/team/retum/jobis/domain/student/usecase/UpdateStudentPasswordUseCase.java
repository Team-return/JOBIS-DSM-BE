package team.retum.jobis.domain.student.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.student.dto.UpdatePasswordRequest;
import team.retum.jobis.domain.user.exception.UserNotFoundException;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.CommandUserPort;
import team.retum.jobis.domain.user.spi.QueryUserPort;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.user.exception.InvalidPasswordException;

@RequiredArgsConstructor
@UseCase
public class UpdateStudentPasswordUseCase {

    private final SecurityPort securityPort;
    private final QueryUserPort queryUserPort;
    private final CommandUserPort commandUserPort;

    public void execute(UpdatePasswordRequest request) {
        Long currentUserId = securityPort.getCurrentUserId();
        User user = queryUserPort.queryUserById(currentUserId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!securityPort.isPasswordMatch(request.getCurrentPassword(), user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        commandUserPort.saveUser(
                user.updatePassword(securityPort.encodePassword(request.getNewPassword()))
        );
    }
}
