package team.retum.jobis.domain.student.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.student.spi.QueryVerifiedStudentPort;

@RequiredArgsConstructor
@UseCase
public class VerifyStudentUseCase {

    private final QueryVerifiedStudentPort queryVerifiedStudentPort;

    public void execute(String gcn, String name) {
        if (!queryVerifiedStudentPort.existsVerifiedStudentByGcnAndName(gcn, name)) {
            throw StudentNotFoundException.EXCEPTION;
        }
    }
}
