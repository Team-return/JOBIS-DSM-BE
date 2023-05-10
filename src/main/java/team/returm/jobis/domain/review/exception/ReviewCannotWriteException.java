package team.returm.jobis.domain.review.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class ReviewCannotWriteException extends JobisException {

    public static final JobisException EXCEPTION = new ReviewCannotWriteException();

    private ReviewCannotWriteException() {
        super(ErrorCode.REVIEW_CANNOT_WRITE);
    }
}
