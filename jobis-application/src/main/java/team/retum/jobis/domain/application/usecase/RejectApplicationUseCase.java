package team.retum.jobis.domain.application.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class RejectApplicationUseCase {

    private final QueryApplicationPort applicationPersistenceAdapter;
    private final CommandApplicationPort commandApplicationPort;

    public void execute(Long applicationId, String rejectReason) {
        Application application = applicationPersistenceAdapter.queryApplicationById(applicationId);

        commandApplicationPort.saveApplication(
                application.rejectApplication(rejectReason)
        );
    }
}
