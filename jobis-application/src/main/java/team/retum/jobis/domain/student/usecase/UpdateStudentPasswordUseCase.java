package team.retum.jobis.domain.student.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.user.checker.UserChecker;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.CommandUserPort;

@RequiredArgsConstructor
@UseCase
public class UpdateStudentPasswordUseCase {

    private final SecurityPort securityPort;
    private final CommandUserPort commandUserPort;
    private final UserChecker userChecker;

    public void execute(String currentPassword, String newPassword) {
        User user = securityPort.getCurrentUser();

        userChecker.checkPasswordMatch(currentPassword, user.getPassword());

        commandUserPort.saveUser(
            user.updatePassword(securityPort.encodePassword(newPassword))
        );
    }
}
