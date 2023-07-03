package team.retum.jobis.domain.review.exception;

import team.retum.jobis.domain.review.exception.error.ReviewErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class ReviewCannotWriteException extends JobisException {

    public static final JobisException EXCEPTION = new ReviewCannotWriteException();

    private ReviewCannotWriteException() {
        super(ReviewErrorCode.REVIEW_CANNOT_WRITE);
    }
}