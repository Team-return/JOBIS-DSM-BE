package team.returm.jobis.domain.review.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.review.domain.Review;
import team.returm.jobis.domain.review.domain.repository.ReviewRepository;
import team.returm.jobis.domain.review.exception.ReviewNotFoundException;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class DeleteReviewService {

    private final ReviewRepository reviewRepository;

    public void execute(String reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        reviewRepository.delete(review);
    }
}
