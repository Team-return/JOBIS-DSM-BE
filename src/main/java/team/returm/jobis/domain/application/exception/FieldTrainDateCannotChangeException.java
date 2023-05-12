package team.returm.jobis.domain.application.exception;

import team.returm.jobis.domain.application.exception.error.ApplicationErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class FieldTrainDateCannotChangeException extends JobisException {

    public static final JobisException EXCEPTION = new FieldTrainDateCannotChangeException();

    private FieldTrainDateCannotChangeException() {
        super(ApplicationErrorCode.FIELD_TRAIN_DATE_CANNOT_CHANGE);
    }
}
