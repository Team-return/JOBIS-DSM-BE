package team.retum.jobis.domain.review.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.review.persistence.entity.ReviewEntity;
import team.retum.jobis.domain.review.persistence.repository.ReviewRepository;
import com.example.jobisapplication.domain.review.exception.ReviewNotFoundException;
import com.example.jobisapplication.common.annotation.Service;

@RequiredArgsConstructor
@Service
public class DeleteReviewService {

    private final ReviewRepository reviewRepository;

    public void execute(String reviewId) {

        ReviewEntity reviewEntity = reviewRepository.findById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        reviewRepository.delete(reviewEntity);
    }
}