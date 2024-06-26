package team.retum.jobis.domain.acceptance.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CancelFieldTraineesUseCase {

    private final QueryApplicationPort applicationRepository;
    private final CommandApplicationPort commandApplicationPort;

    public void execute(List<Long> applicationIds) {
        List<Application> applications = applicationRepository.getAllByIdInOrThrow(applicationIds);

        applications.forEach(
            application -> Application.checkApplicationStatus(
                application.getApplicationStatus(),
                ApplicationStatus.FIELD_TRAIN
            )
        );

        commandApplicationPort.updateApplicationStatus(
            ApplicationStatus.PASS,
            applicationIds
        );
    }
}
