package com.example.jobisapplication.domain.review.exception;

import com.example.jobisapplication.domain.review.exception.error.ReviewErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class ReviewCannotWriteException extends JobisException {

    public static final JobisException EXCEPTION = new ReviewCannotWriteException();

    private ReviewCannotWriteException() {
        super(ReviewErrorCode.REVIEW_CANNOT_WRITE);
    }
}
