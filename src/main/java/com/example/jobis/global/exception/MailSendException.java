package com.example.jobis.global.exception;

import com.example.jobis.global.error.exception.ErrorCode;
import com.example.jobis.global.error.exception.JobisException;

public class MailSendException extends JobisException {

    public static final JobisException EXCEPTION =
            new MailSendException();

    private MailSendException() {
        super(ErrorCode.MAIL_SEND_FAIL);
    }
}
