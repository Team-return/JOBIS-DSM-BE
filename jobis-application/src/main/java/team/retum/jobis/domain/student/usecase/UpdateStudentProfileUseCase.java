package team.retum.jobis.domain.student.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.CommandStudentPort;
import team.retum.jobis.domain.student.spi.QueryStudentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class UpdateStudentProfileUseCase {

    private final SecurityPort securityPort;
    private final QueryStudentPort queryStudentPort;
    private final CommandStudentPort commandStudentPort;

    public void execute(String profileImageUrl) {
        Long currentUserId = securityPort.getCurrentUserId();
        Student student = queryStudentPort.queryStudentById(currentUserId)
                        .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        commandStudentPort.saveStudent(
                student.changeStudentProfile(profileImageUrl)
        );
    }
}
