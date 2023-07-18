package team.retum.jobis.domain.review.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.review.persistence.Review;
import team.retum.jobis.domain.review.persistence.repository.ReviewRepository;
import team.retum.jobis.domain.review.exception.ReviewNotFoundException;
import com.example.jobisapplication.common.annotation.Service;

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
