package team.retum.jobis.domain.notification.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.notification.exception.error.NotificationErrorCode;

public class NotificationNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new NotificationNotFoundException();

    private NotificationNotFoundException() {
        super(NotificationErrorCode.NOTIFICATION_NOT_FOUND);
    }
}
