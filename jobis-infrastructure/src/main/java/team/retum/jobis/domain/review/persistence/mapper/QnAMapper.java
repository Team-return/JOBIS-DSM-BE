package team.retum.jobis.domain.review.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.review.model.QnA;
import team.retum.jobis.domain.review.persistence.entity.QnAEntity;
import team.retum.jobis.domain.review.persistence.entity.ReviewEntity;
import team.retum.jobis.domain.review.persistence.repository.ReviewJpaRepository;

@RequiredArgsConstructor
@Component
public class QnAMapper {

    private final ReviewJpaRepository reviewJpaRepository;

    public QnAEntity toEntity(QnA domain) {
        ReviewEntity review = reviewJpaRepository.getReferenceById(domain.getReviewId());

        return QnAEntity.builder()
            .id(domain.getId())
            .question(domain.getQuestion())
            .answer(domain.getAnswer())
            .review(review)
            .build();
    }

    public QnA toDomain(QnAEntity entity) {
        return QnA.builder()
            .id(entity.getId())
            .answer(entity.getAnswer())
            .question(entity.getQuestion())
            .reviewId(entity.getReview().getId())
            .build();
    }
}
