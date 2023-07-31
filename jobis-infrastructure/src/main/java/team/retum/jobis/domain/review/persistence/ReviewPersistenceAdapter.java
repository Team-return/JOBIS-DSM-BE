package team.retum.jobis.domain.review.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.persistence.mapper.ReviewMapper;
import team.retum.jobis.domain.review.persistence.repository.ReviewRepository;
import team.retum.jobis.domain.review.spi.ReviewPort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReviewPersistenceAdapter implements ReviewPort {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public void saveReview(Review review) {
        reviewRepository.save(reviewMapper.toEntity(review));
    }

    @Override
    public void deleteReview(Review review) {
        reviewRepository.delete(reviewMapper.toEntity(review));
    }

    @Override
    public boolean existsByCompanyIdAndStudentName(Long companyId, String studentName) {
        return reviewRepository.existsByCompanyIdAndStudentName(companyId, studentName);
    }

    @Override
    public Optional<Review> findReviewById(String reviewId) {
        return reviewRepository.findById(reviewId)
                .map(reviewMapper::toDomain);
    }

    @Override
    public List<Review> queryAllReviewsByCompanyId(Long companyId) {
        return reviewRepository.findAllByCompanyId(companyId).stream()
                .map(reviewMapper::toDomain)
                .toList();
    }

    @Override
    public Long queryReviewCountByCompanyId(Long companyId) {
        return reviewRepository.countByCompanyId(companyId);
    }
}
