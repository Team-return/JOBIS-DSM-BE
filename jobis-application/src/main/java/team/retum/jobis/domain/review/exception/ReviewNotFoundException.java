package team.retum.jobis.domain.review.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.review.exception.error.ReviewErrorCode;

public class ReviewNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new ReviewNotFoundException();

    private ReviewNotFoundException() {
        super(ReviewErrorCode.REVIEW_NOT_FOUND);
    }
}
