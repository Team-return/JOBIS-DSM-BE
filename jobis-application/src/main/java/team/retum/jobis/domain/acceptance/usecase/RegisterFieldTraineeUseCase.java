package team.retum.jobis.domain.acceptance.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.acceptance.dto.request.RegisterFieldTraineeRequest;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class RegisterFieldTraineeUseCase {

    private final CommandApplicationPort commandApplicationPort;
    private final QueryApplicationPort queryApplicationPort;

    public void execute(RegisterFieldTraineeRequest request) {
        List<Application> applications = queryApplicationPort.queryApplicationsByIds(request.applicationIds());
        if (request.applicationIds().size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        List<Application> converted = applications.stream()
                .map(application ->
                        application.toFieldTrain(request.startDate(), request.endDate())
                ).toList();

        commandApplicationPort.saveAllApplications(converted);
    }
}
