package team.returm.jobis.domain.student.exception;

import team.returm.jobis.domain.student.exception.error.StudentErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class ClassRoomNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new ClassRoomNotFoundException();

    private ClassRoomNotFoundException() {
        super(StudentErrorCode.CLASSROOM_NOT_FOUND);
    }
}
