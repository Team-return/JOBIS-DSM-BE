package team.retum.jobis.domain.banner.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.common.error.ErrorProperty;
import team.retum.jobis.common.error.HttpStatus;

@Getter
@AllArgsConstructor
public enum BannerErrorCode implements ErrorProperty {

    BANNER_NOT_FOUND(HttpStatus.NOT_FOUND, "Banner Not Found");

    private final HttpStatus status;
    private final String message;
}
