package team.retum.jobis.domain.review.exception;

import team.retum.jobis.domain.review.exception.error.ReviewErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class ReviewNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new ReviewNotFoundException();

    private ReviewNotFoundException() {
        super(ReviewErrorCode.REVIEW_NOT_FOUND);
    }
}
