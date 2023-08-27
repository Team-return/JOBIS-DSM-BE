package team.retum.jobis.global.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.global.error.exception.GlobalErrorCode;

public class FailedSendingMessagesException extends JobisException {

    public static final JobisException EXCEPTION = new FailedSendingMessagesException();

    private FailedSendingMessagesException() {
        super(GlobalErrorCode.FAILED_SENDING_MESSAGE);
    }
}
