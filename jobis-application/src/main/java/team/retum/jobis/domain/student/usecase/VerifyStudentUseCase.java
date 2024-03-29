package team.retum.jobis.domain.student.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.student.exception.StudentAlreadyExistsException;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.student.model.SchoolNumber;
import team.retum.jobis.domain.student.spi.QueryStudentPort;
import team.retum.jobis.domain.student.spi.QueryVerifiedStudentPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class VerifyStudentUseCase {

    private final QueryVerifiedStudentPort queryVerifiedStudentPort;
    private final QueryStudentPort queryStudentPort;

    public void execute(String gcn, String name) {
        SchoolNumber parseSchoolNumber = SchoolNumber.parseSchoolNumber(gcn);

        if (queryStudentPort.existsBySchoolNumberAndName(parseSchoolNumber, name)) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }

        if (!queryVerifiedStudentPort.existsVerifiedStudentByGcnAndName(gcn, name)) {
            throw StudentNotFoundException.EXCEPTION;
        }
    }
}
