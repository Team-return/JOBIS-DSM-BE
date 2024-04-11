package team.retum.jobis.domain.student.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.CommandStudentPort;

@RequiredArgsConstructor
@UseCase
public class UpdateStudentProfileUseCase {

    private final SecurityPort securityPort;
    private final CommandStudentPort commandStudentPort;

    public void execute(String profileImageUrl) {
        Student student = securityPort.getCurrentStudent();

        commandStudentPort.save(
            student.changeStudentProfile(profileImageUrl)
        );
    }
}
