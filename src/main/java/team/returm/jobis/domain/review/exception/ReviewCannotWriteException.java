package team.returm.jobis.domain.review.exception;

import team.returm.jobis.domain.review.exception.error.ReviewErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class ReviewCannotWriteException extends JobisException {

    public static final JobisException EXCEPTION = new ReviewCannotWriteException();

    private ReviewCannotWriteException() {
        super(ReviewErrorCode.REVIEW_CANNOT_WRITE);
    }
}
