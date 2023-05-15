package team.returm.jobis.global.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class MailSendException extends JobisException {

    public static final JobisException EXCEPTION =
            new MailSendException();

    private MailSendException() {
        super(GlobalErrorCode.MAIL_SEND_FAIL);
    }
}
