package team.returm.jobis.domain.bookmark.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class BookmarkNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new BookmarkNotFoundException();

    private BookmarkNotFoundException() {
        super(GlobalErrorCode.BOOKMARK_NOT_FOUND);
    }
}
