package team.retum.jobis.domain.notice.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.common.error.ErrorProperty;
import team.retum.jobis.common.error.HttpStatus;

@Getter
@AllArgsConstructor
public enum NoticeErrorCode implements ErrorProperty {

    NOTICE_NOT_FOUND(HttpStatus.NOT_FOUND, "Notice Not Found");

    private final HttpStatus status;
    private final String message;
}
