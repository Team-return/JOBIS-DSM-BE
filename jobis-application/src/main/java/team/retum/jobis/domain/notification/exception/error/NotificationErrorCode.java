package team.retum.jobis.domain.notification.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.common.error.ErrorProperty;
import team.retum.jobis.common.error.HttpStatus;

@Getter
@AllArgsConstructor
public enum NotificationErrorCode implements ErrorProperty {

    NOTIFICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Notification Not Found"),

    FAILED_TO_SUBSCRIPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to subscription"),
    FAILED_TO_UNSUBSCRIPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to unsubscription");


    private final HttpStatus status;
    private final String message;
}
