package team.returm.jobis.domain.bookmark.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum BookmarkErrorCode implements ErrorProperty {

    BOOKMARK_NOT_FOUND(404, "Bookmark Not Found");

    private final Integer status;
    private final String message;
}
