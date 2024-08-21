package team.retum.jobis.domain.bookmark.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.common.error.ErrorProperty;
import team.retum.jobis.common.error.HttpStatus;

@Getter
@AllArgsConstructor
public enum BookmarkErrorCode implements ErrorProperty {

    BOOKMARK_NOT_FOUND(HttpStatus.NOT_FOUND, "Bookmark Not Found"),

    BOOKMARK_ALREADY_EXISTS(HttpStatus.CONFLICT, "Bookmark Already Exists");

    private final HttpStatus status;
    private final String message;
}
