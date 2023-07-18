package com.example.jobisapplication.domain.review.exception;

import com.example.jobisapplication.domain.review.exception.error.ReviewErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class ReviewAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION = new ReviewAlreadyExistsException();

    private ReviewAlreadyExistsException() {
        super(ReviewErrorCode.REVIEW_ALREADY_EXISTS);
    }
}
