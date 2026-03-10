package team.retum.jobis.domain.interview.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.interview.exception.error.InterviewErrorCode;

public class InvalidStudentIdException extends JobisException {

    public static final InvalidStudentIdException EXCEPTION = new InvalidStudentIdException();

    private InvalidStudentIdException() {
        super(InterviewErrorCode.INVALID_STUDENT_ID);
    }
}
