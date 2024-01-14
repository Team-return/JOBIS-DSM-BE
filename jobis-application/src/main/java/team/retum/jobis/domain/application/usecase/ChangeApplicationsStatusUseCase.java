package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ChangeApplicationsStatusUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final CommandApplicationPort commandApplicationPort;

    public void execute(List<Long> applicationIds, ApplicationStatus status) {
        Long applicationCount = queryApplicationPort.queryApplicationCountByIds(applicationIds);

        if (applicationIds.size() != applicationCount.intValue()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        commandApplicationPort.changeApplicationStatus(status, applicationIds);
    }
}
