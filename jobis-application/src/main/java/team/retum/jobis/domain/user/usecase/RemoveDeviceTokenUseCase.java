package team.retum.jobis.domain.user.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.CommandUserPort;

@RequiredArgsConstructor
@UseCase
public class RemoveDeviceTokenUseCase {

    private final SecurityPort securityPort;
    private final CommandUserPort commandUserPort;

    public void execute() {
        User user = securityPort.getCurrentUser();

        commandUserPort.save(
            user.removeDeviceToken()
        );
    }
}
