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
        SchoolNumber schoolNumber = SchoolNumber.parseSchoolNumber(gcn);

        checkStudentIsSignUp(schoolNumber, name);
        checkStudentSignUpIsPossible(gcn, name);
    }

    private void checkStudentIsSignUp(SchoolNumber schoolNumber, String name) {
        if (queryStudentPort.existsBySchoolNumberAndName(schoolNumber, name)) {
            throw StudentAlreadyExistsException.EXCEPTION;
        }
    }

    private void checkStudentSignUpIsPossible(String gcn, String name) {
        if (!queryVerifiedStudentPort.existsVerifiedStudentByGcnAndName(gcn, name)) {
            throw StudentNotFoundException.EXCEPTION;
        }
    }
}
