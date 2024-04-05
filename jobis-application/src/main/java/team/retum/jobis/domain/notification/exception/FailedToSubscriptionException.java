package team.retum.jobis.domain.notification.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.notification.exception.error.NotificationErrorCode;

public class FailedToSubscriptionException extends JobisException {

    public static final JobisException EXCEPTION = new FailedToSubscriptionException();

    private FailedToSubscriptionException() {
        super(NotificationErrorCode.FAILED_TO_SUBSCRIPTION);
    }
}
