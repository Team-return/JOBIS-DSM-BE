package com.example.jobisapplication.domain.review.spi;

import com.example.jobisapplication.domain.review.model.Review;

public interface CommandReviewPort {
    void saveReview(Review review);

    void deleteReview(Review review);
}
