package team.returm.jobis.domain.bookmark.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum BookmarkErrorCode implements ErrorProperty {

    BOOKMARK_NOT_FOUND(HttpStatus.NOT_FOUND, "Bookmark Not Found"),

    BOOKMARK_ALREADY_EXISTS(HttpStatus.CONFLICT, "Bookmark Already Exists");

    private final HttpStatus status;
    private final String message;
}
