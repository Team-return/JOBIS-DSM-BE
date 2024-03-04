package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.application.dto.request.AttachmentRequest;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationAttachment;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ReapplyUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final CommandApplicationPort commandApplicationPort;

    public void execute(Long applicationId, List<AttachmentRequest> attachmentRequests) {
        Application application = queryApplicationPort.queryApplicationById(applicationId)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);

        application.checkApplicationStatus(application.getApplicationStatus(), ApplicationStatus.REJECTED, ApplicationStatus.REQUESTED);

        commandApplicationPort.saveApplication(
                application.reapply(
                        attachmentRequests.stream()
                                .map(attachment -> new ApplicationAttachment(attachment.url(), attachment.type()))
                                .toList()
                )
        );
    }
}
