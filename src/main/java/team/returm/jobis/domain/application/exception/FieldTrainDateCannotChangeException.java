package team.returm.jobis.domain.application.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class FieldTrainDateCannotChangeException extends JobisException {

    public static final JobisException EXCEPTION = new FieldTrainDateCannotChangeException();

    private FieldTrainDateCannotChangeException() {
        super(ErrorCode.FIELD_TRAIN_DATE_CANNOT_Change);
    }
}
