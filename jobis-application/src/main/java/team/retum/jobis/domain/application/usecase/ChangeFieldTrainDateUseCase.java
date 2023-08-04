package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.application.dto.request.ChangeFieldTrainDateRequest;
import team.retum.jobis.domain.application.exception.InvalidDateException;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ChangeFieldTrainDateUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final CommandApplicationPort commandApplicationPort;

    public void execute(ChangeFieldTrainDateRequest request) {

        if (request.getStartDate().isBefore(LocalDate.now())) {
            throw InvalidDateException.EXCEPTION;
        }

        List<Application> applications = queryApplicationPort.queryApplicationsByIds(request.getApplicationIds());

        applications
                .forEach(application ->
                        application.checkApplicationStatus(application.getApplicationStatus(), ApplicationStatus.FIELD_TRAIN)
                );

        commandApplicationPort.updateFieldTrainDate(
                request.getStartDate(),
                request.getEndDate(),
                request.getApplicationIds()
        );
    }
}
