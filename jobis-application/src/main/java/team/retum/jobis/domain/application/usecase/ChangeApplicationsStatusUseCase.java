package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.event.ApplicationsStatusChangedEvent;
import team.retum.jobis.domain.application.exception.ApplicationCannotChangeException;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.user.model.User;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ChangeApplicationsStatusUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final CommandApplicationPort commandApplicationPort;
    private final PublishEventPort eventPublisher;
    private final SecurityPort securityPort;

    public void execute(List<Long> applicationIds, ApplicationStatus status) {
        User currentUser = securityPort.getCurrentUser();
        List<Application> applications = queryApplicationPort.getAllByIdInOrThrow(applicationIds);

        validateAuthority(currentUser, applicationIds);

        commandApplicationPort.updateApplicationStatus(status, applicationIds);
        eventPublisher.publishEvent(new ApplicationsStatusChangedEvent(applications, status));
    }

    private void validateAuthority(User currentUser, List<Long> applicationIds) {
        if (currentUser.getAuthority() == Authority.TEACHER) {
            return;
        }
        if (currentUser.getAuthority() == Authority.COMPANY) {
            Long currentCompanyId = securityPort.getCurrentCompany().getId();

            if (!queryApplicationPort.existsAllByApplicationIdsAndCompanyId(applicationIds, currentCompanyId)) {
                throw ApplicationCannotChangeException.EXCEPTION;
            }
            return;
        }
        throw ApplicationCannotChangeException.EXCEPTION;
    }
}
