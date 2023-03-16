package team.returm.jobis.global.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class MailSendException extends JobisException {

    public static final JobisException EXCEPTION =
            new MailSendException();

    private MailSendException() {
        super(ErrorCode.MAIL_SEND_FAIL);
    }
}
