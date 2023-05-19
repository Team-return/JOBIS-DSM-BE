package team.returm.jobis.domain.review.exception;

import team.returm.jobis.domain.review.exception.error.ReviewErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class ReviewAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new ReviewAlreadyExistsException();

    private ReviewAlreadyExistsException() {
        super(ReviewErrorCode.REVIEW_ALREADY_EXISTS);
    }
}
