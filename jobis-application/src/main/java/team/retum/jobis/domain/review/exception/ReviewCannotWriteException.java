package team.retum.jobis.domain.review.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.review.exception.error.ReviewErrorCode;

public class ReviewCannotWriteException extends JobisException {

    public static final JobisException EXCEPTION = new ReviewCannotWriteException();

    private ReviewCannotWriteException() {
        super(ReviewErrorCode.REVIEW_CANNOT_WRITE);
    }
}
