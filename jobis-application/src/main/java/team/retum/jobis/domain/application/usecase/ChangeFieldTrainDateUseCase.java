package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.application.dto.request.ChangeFieldTrainDateRequest;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ChangeFieldTrainDateUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final CommandApplicationPort commandApplicationPort;

    public void execute(ChangeFieldTrainDateRequest request) {
        List<Application> applications = queryApplicationPort.getByIdsOrThrow(request.applicationIds());

        applications.forEach(
            application -> Application.checkApplicationStatus(
                application.getApplicationStatus(),
                ApplicationStatus.FIELD_TRAIN
            )
        );

        commandApplicationPort.updateFieldTrainDate(
            request.startDate(),
            request.endDate(),
            request.applicationIds()
        );
    }
}
