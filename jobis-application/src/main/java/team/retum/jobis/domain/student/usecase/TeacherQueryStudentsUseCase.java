package team.retum.jobis.domain.student.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.student.dto.response.TeacherQueryStudentsResponse;
import team.retum.jobis.domain.student.spi.QueryStudentPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryStudentsUseCase {

    private final QueryStudentPort queryStudentPort;

    public TeacherQueryStudentsResponse execute(String name) {
        return new TeacherQueryStudentsResponse(
            queryStudentPort.getStudentsByName(name).stream()
                .map(TeacherQueryStudentsResponse.TeacherStudentResponse::from)
                .toList()
        );
    }
}
