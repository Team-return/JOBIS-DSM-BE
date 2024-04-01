package team.retum.jobis.global.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.global.error.exception.GlobalErrorCode;

public class MailSendException extends JobisException {

    public static final JobisException EXCEPTION =
        new MailSendException();

    private MailSendException() {
        super(GlobalErrorCode.MAIL_SEND_FAIL);
    }
}
