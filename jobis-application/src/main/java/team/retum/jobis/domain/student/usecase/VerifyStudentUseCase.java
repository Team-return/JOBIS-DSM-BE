package team.retum.jobis.domain.student.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.student.spi.QueryVerifiedStudentPort;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;

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
