package team.returm.jobis.domain.bookmark.exception;

import team.returm.jobis.domain.bookmark.exception.error.BookmarkErrorCode;
import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class BookmarkNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new BookmarkNotFoundException();

    private BookmarkNotFoundException() {
        super(BookmarkErrorCode.BOOKMARK_NOT_FOUND);
    }
}
