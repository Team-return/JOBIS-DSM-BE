package team.retum.jobis.domain.bookmark.exception;

import team.retum.jobis.domain.bookmark.exception.error.BookmarkErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class BookmarkAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new BookmarkAlreadyExistsException();

    private BookmarkAlreadyExistsException() {
        super(BookmarkErrorCode.BOOKMARK_ALREADY_EXISTS);
    }
}
