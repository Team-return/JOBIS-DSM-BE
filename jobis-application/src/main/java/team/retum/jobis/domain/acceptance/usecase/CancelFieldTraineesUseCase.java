package team.retum.jobis.domain.acceptance.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.acceptance.dto.request.CancelFieldTraineesRequest;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CancelFieldTraineesUseCase {

    private final QueryApplicationPort applicationRepository;
    private final CommandApplicationPort commandApplicationPort;

    public void execute(CancelFieldTraineesRequest request) {
        List<Application> applications = applicationRepository.queryApplicationsByIds(request.getApplicationIds());

        if (request.getApplicationIds().size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        applications.forEach(application ->
                application.checkApplicationStatus(application.getApplicationStatus(), ApplicationStatus.FIELD_TRAIN)
        );

        commandApplicationPort.changeApplicationStatus(
                ApplicationStatus.PASS,
                request.getApplicationIds()
        );
    }
}
