package team.retum.jobis.domain.acceptance.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.acceptance.dto.request.RegisterFieldTraineeRequest;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class RegisterFieldTraineeService {

    private final CommandApplicationPort commandApplicationPort;
    private final QueryApplicationPort queryApplicationPort;

    public void execute(RegisterFieldTraineeRequest request) {
        List<Application> applications = queryApplicationPort.queryApplicationsByIds(request.getApplicationIds());

        if (request.getApplicationIds().size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        List<Application> converted = applications.stream()
                        .map( application ->
                                application.toFieldTrain(request.getStartDate(), request.getEndDate())
                        ).toList();

        commandApplicationPort.saveAllApplications(converted);
    }
}
