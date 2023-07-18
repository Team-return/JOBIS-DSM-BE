package com.example.jobisapplication.domain.application.exception;

import com.example.jobisapplication.common.error.JobisException;
import com.example.jobisapplication.domain.application.exception.error.ApplicationErrorCode;

public class FieldTrainDateCannotChangeException extends JobisException {

    public static final JobisException EXCEPTION = new FieldTrainDateCannotChangeException();

    private FieldTrainDateCannotChangeException() {
        super(ApplicationErrorCode.FIELD_TRAIN_DATE_CANNOT_CHANGE);
    }
}
