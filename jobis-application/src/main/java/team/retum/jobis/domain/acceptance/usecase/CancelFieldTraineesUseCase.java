package team.retum.jobis.domain.acceptance.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
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
        List<Application> applications = applicationRepository.queryApplicationsByIds(applicationIds);

        if (applicationIds.size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        applications.forEach(
            application -> Application.checkApplicationStatus(
                application.getApplicationStatus(),
                ApplicationStatus.FIELD_TRAIN
            )
        );

        commandApplicationPort.changeApplicationStatus(
            ApplicationStatus.PASS,
            applicationIds
        );
    }
}
