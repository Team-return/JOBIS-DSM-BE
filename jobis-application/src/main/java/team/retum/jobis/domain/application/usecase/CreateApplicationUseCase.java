package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.dto.request.AttachmentRequest;
import team.retum.jobis.domain.application.exception.ApplicationAlreadyExistsException;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationAttachment;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.student.model.Student;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CreateApplicationUseCase {

    private final CommandApplicationPort commandApplicationPort;
    private final QueryApplicationPort queryApplicationPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SecurityPort securityPort;

    public void execute(Long recruitmentId, List<AttachmentRequest> attachmentRequests) {
        Student student = securityPort.getCurrentStudent();
        Recruitment recruitment = queryRecruitmentPort.getByIdOrThrow(recruitmentId);

        recruitment.checkIsApplicable(student.getEntranceYear());
        checkApplicationDuplicated(student.getId());
        checkApplicationAlreadyApply(student.getId(), recruitment.getId());

        List<ApplicationAttachment> attachments = ApplicationAttachment.from(attachmentRequests);
        commandApplicationPort.save(
            Application.builder()
                .studentId(student.getId())
                .recruitmentId(recruitment.getId())
                .applicationStatus(ApplicationStatus.REQUESTED)
                .attachments(attachments)
                .build()
        );
    }

    private void checkApplicationDuplicated(Long studentId) {
        if (queryApplicationPort.existsByStudentIdAndApplicationStatusIn(
            studentId,
            ApplicationStatus.DUPLICATE_CHECK
        )) {
            throw ApplicationAlreadyExistsException.EXCEPTION;
        }
    }

    private void checkApplicationAlreadyApply(Long studentId, Long recruitmentId) {
        if (queryApplicationPort.existsByStudentIdAndRecruitmentId(studentId, recruitmentId)) {
            throw ApplicationAlreadyExistsException.EXCEPTION;
        }
    }
}
