package team.retum.jobis.domain.student.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.user.exception.InvalidPasswordException;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.CommandUserPort;

@RequiredArgsConstructor
@UseCase
public class UpdateStudentPasswordUseCase {

    private final SecurityPort securityPort;
    private final CommandUserPort commandUserPort;

    public void execute(String currentPassword, String newPassword) {
        User user = securityPort.getCurrentUser();

        if (!securityPort.isPasswordMatch(currentPassword, user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        commandUserPort.saveUser(
                user.updatePassword(securityPort.encodePassword(newPassword))
        );
    }
}
