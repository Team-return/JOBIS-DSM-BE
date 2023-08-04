package team.retum.jobis.domain.student.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.student.dto.StudentMyPageResponse;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.QueryStudentPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class StudentMyPageUseCase {

    private final SecurityPort securityPort;
    private final QueryStudentPort queryStudentPort;

    public StudentMyPageResponse execute() {
        Long currentUserId = securityPort.getCurrentUserId();
        Student student = queryStudentPort.queryStudentById(currentUserId)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        return StudentMyPageResponse.of(student);
    }
}
