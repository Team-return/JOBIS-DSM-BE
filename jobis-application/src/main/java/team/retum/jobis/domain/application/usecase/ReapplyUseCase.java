package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.dto.request.AttachmentRequest;
import team.retum.jobis.domain.application.exception.ApplicationInProgressException;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationAttachment;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.QueryStudentPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ReapplyUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final CommandApplicationPort commandApplicationPort;
    private final QueryStudentPort queryStudentPort;
    private final SecurityPort securityPort;

    public void execute(Long applicationId, List<AttachmentRequest> attachmentRequests) {
        Student student = queryStudentPort.getByIdWithPessimisticLock(securityPort.getCurrentUserId())
            .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
        Application application = queryApplicationPort.getByIdOrThrow(applicationId);

        application.checkIsOwner(student.getId());
        Application.checkApplicationStatus(
            application.getApplicationStatus(),
            ApplicationStatus.REJECTED, ApplicationStatus.REQUESTED
        );
        checkOtherApplicationInProgress(student.getId(), applicationId);

        commandApplicationPort.deleteAllByApplicationId(applicationId);
        commandApplicationPort.deleteRejectionAttachmentsByApplicationId(applicationId);
        commandApplicationPort.save(
            application.reapply(ApplicationAttachment.from(attachmentRequests))
        );
    }

    private void checkOtherApplicationInProgress(Long studentId, Long applicationId) {
        if (queryApplicationPort.existsByStudentIdAndApplicationStatusInAndIdNot(
            studentId,
            ApplicationStatus.DUPLICATE_CHECK,
            applicationId
        )) {
            throw ApplicationInProgressException.EXCEPTION;
        }
    }
}
