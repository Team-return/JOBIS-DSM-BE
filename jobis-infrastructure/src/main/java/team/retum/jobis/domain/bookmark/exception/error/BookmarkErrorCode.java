package team.retum.jobis.domain.bookmark.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import team.retum.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum BookmarkErrorCode implements ErrorProperty {

    BOOKMARK_NOT_FOUND(HttpStatus.NOT_FOUND, "BookmarkEntity Not Found"),

    BOOKMARK_ALREADY_EXISTS(HttpStatus.CONFLICT, "BookmarkEntity Already Exists");

    private final HttpStatus status;
    private final String message;
}
