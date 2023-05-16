package team.returm.jobis.domain.bookmark.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum BookmarkErrorCode implements ErrorProperty {

    BOOKMARK_NOT_FOUND(404, "Bookmark Not Found"),

    BOOKMARK_ALREADY_EXISTS(409, "Bookmark Already Exists");

    private final int status;
    private final String message;
}
