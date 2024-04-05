package team.retum.jobis.domain.notification.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.notification.exception.error.NotificationErrorCode;

public class FailedToUnsubscriptionException extends JobisException {

    public static final JobisException EXCEPTION = new FailedToUnsubscriptionException();

    private FailedToUnsubscriptionException() {
        super(NotificationErrorCode.FAILED_TO_UNSUBSCRIPTION);
    }
}