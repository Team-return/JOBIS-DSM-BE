package com.example.jobisapplication.domain.review.exception;

import com.example.jobisapplication.domain.review.exception.error.ReviewErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class ReviewNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new ReviewNotFoundException();

    private ReviewNotFoundException() {
        super(ReviewErrorCode.REVIEW_NOT_FOUND);
    }
}
