package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.student.model.Student;

@RequiredArgsConstructor
@UseCase
public class DeleteApplicationUseCase {

    private final CommandApplicationPort commandApplicationPort;
    private final QueryApplicationPort queryApplicationPort;
    private final SecurityPort securityPort;

    public void execute(Long applicationId) {
        Student student = securityPort.getCurrentStudent();

        Application application = queryApplicationPort.queryApplicationById(applicationId)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);

        application.checkIsDeletable(student);

        commandApplicationPort.deleteApplication(application);
    }
}
