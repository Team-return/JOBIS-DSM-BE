package team.retum.jobis.global.exception;

import team.retum.jobis.global.error.exception.GlobalErrorCode;
import team.retum.jobis.common.error.JobisException;

public class MailSendException extends JobisException {

    public static final JobisException EXCEPTION =
            new MailSendException();

    private MailSendException() {
        super(GlobalErrorCode.MAIL_SEND_FAIL);
    }
}
