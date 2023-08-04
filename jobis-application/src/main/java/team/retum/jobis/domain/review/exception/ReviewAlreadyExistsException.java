package team.retum.jobis.domain.review.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.review.exception.error.ReviewErrorCode;

public class ReviewAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new ReviewAlreadyExistsException();

    private ReviewAlreadyExistsException() {
        super(ReviewErrorCode.REVIEW_ALREADY_EXISTS);
    }
}
