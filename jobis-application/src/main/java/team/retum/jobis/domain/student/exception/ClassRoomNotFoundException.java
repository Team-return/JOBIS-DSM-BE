package team.retum.jobis.domain.student.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.student.exception.error.StudentErrorCode;

public class ClassRoomNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new ClassRoomNotFoundException();

    private ClassRoomNotFoundException() {
        super(StudentErrorCode.CLASSROOM_NOT_FOUND);
    }
}
