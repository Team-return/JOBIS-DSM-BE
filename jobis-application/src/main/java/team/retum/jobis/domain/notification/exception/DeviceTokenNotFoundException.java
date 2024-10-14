package team.retum.jobis.domain.notification.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.notification.exception.error.NotificationErrorCode;

public class DeviceTokenNotFoundException extends JobisException {

    public DeviceTokenNotFoundException() {
        super(NotificationErrorCode.DEVICE_TOKEN_NOT_FOUND);
    }
}
