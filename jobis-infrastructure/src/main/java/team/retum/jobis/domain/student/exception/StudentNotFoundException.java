package team.retum.jobis.domain.student.exception;

import team.retum.jobis.domain.student.exception.error.StudentErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class StudentNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new StudentNotFoundException();

    private StudentNotFoundException() {
        super(StudentErrorCode.STUDENT_NOT_FOUND);
    }
}
