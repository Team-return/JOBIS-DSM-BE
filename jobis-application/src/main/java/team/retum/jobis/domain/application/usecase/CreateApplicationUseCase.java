package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.dto.request.CreateApplicationRequest;
import team.retum.jobis.domain.application.exception.ApplicationAlreadyExistsException;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationAttachment;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
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

    public void execute(CreateApplicationRequest request, Long recruitmentId) {
        Student student = securityPort.getCurrentStudent();

        Recruitment recruitment = queryRecruitmentPort.queryRecruitmentById(recruitmentId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
        recruitment.checkIsApplicable(student.getSchoolNumber().getGrade());

        if (queryApplicationPort.existsApplicationByStudentIdAndApplicationStatusIn(
                student.getId(), ApplicationStatus.DUPLICATE_CHECK
        )) {
            throw ApplicationAlreadyExistsException.EXCEPTION;
        }

        if (queryApplicationPort.existsApplicationByStudentIdAndRecruitmentId(student.getId(), recruitment.getId())) {
            throw ApplicationAlreadyExistsException.EXCEPTION;
        }

        List<ApplicationAttachment> attachments = request.getAttachments()
                .stream()
                .map(attachment -> new ApplicationAttachment(attachment.getUrl(), attachment.getType()))
                .toList();

        commandApplicationPort.saveApplication(
                Application.builder()
                        .studentId(student.getId())
                        .recruitmentId(recruitment.getId())
                        .applicationStatus(ApplicationStatus.REQUESTED)
                        .attachments(attachments)
                        .build()
        );
    }
}
