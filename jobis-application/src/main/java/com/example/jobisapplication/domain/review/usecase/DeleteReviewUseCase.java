package com.example.jobisapplication.domain.review.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.review.model.Review;
import com.example.jobisapplication.domain.review.spi.CommandReviewPort;
import com.example.jobisapplication.domain.review.spi.QueryReviewPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.review.exception.ReviewNotFoundException;

@RequiredArgsConstructor
@UseCase
public class DeleteReviewUseCase {

    private final QueryReviewPort queryReviewPort;
    private final CommandReviewPort commandReviewPort;

    public void execute(String reviewId) {
        Review review = queryReviewPort.findReviewById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        commandReviewPort.deleteReview(review);
    }
}
