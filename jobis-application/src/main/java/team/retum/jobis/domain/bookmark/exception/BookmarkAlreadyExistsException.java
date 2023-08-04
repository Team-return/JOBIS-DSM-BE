package team.retum.jobis.domain.bookmark.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.bookmark.exception.error.BookmarkErrorCode;

public class BookmarkAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new BookmarkAlreadyExistsException();

    private BookmarkAlreadyExistsException() {
        super(BookmarkErrorCode.BOOKMARK_ALREADY_EXISTS);
    }
}
