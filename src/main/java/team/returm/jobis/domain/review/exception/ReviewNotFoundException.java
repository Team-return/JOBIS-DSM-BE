package team.returm.jobis.domain.review.exception;

import team.returm.jobis.domain.review.exception.error.ReviewErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class ReviewNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new ReviewNotFoundException();

    private ReviewNotFoundException() {
        super(ReviewErrorCode.REVIEW_NOT_FOUND);
    }
}
