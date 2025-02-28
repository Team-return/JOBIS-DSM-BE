package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.application.dto.request.CreateNonSchoolContactPassApplicationsRequest;
import team.retum.jobis.domain.application.exception.ApplicationAlreadyExistsException;
import team.retum.jobis.domain.application.exception.InvalidGradeException;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationAttachment;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.model.AttachmentType;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.student.model.SchoolNumber;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.QueryStudentPort;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateNonSchoolContactPassApplicationsUseCase {
    private final CommandApplicationPort commandApplicationPort;
    private final QueryStudentPort queryStudentPort;
    private final QueryApplicationPort queryApplicationPort;
    private final QueryRecruitmentPort queryRecruitmentPort;

    public void execute(CreateNonSchoolContactPassApplicationsRequest request) {
        Recruitment recruitment = queryRecruitmentPort.getByIdOrThrow(request.recruitmentId());
        recruitment.checkIsManualAdd();

        List<SchoolNumber> schoolNumbers = request.studentGcns()
                .stream()
                .map(gcn -> {
                    if (gcn.charAt(0) != '3') {
                        throw InvalidGradeException.EXCEPTION;
                    }
                    return SchoolNumber.parseSchoolNumber(gcn);
                }).toList();

        List<Student> students = queryStudentPort.getStudentsByGradeAndClassRoomAndNumberAndEntranceYearOrThrow(schoolNumbers, Student.getEntranceYear(3));
        List<Application> applications = new ArrayList<>();
        ApplicationAttachment attachment = new ApplicationAttachment("", AttachmentType.NONE);

        for (Student student : students) {
            long id = student.getId();

            checkApplicationDuplicated(id);
            checkApplicationAlreadyApply(id, request.recruitmentId());

            applications.add(
                    Application.builder()
                            .studentId(id)
                            .recruitmentId(request.recruitmentId())
                            .applicationStatus(ApplicationStatus.PASS)
                            .attachments(List.of(attachment))
                            .build()
            );
        }

        commandApplicationPort.saveAll(applications);
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
