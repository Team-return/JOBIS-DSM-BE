package team.retum.jobis.domain.review.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.review.model.QnA;
import team.retum.jobis.domain.review.persistence.entity.QnAEntity;
import team.retum.jobis.domain.review.persistence.entity.QuestionEntity;
import team.retum.jobis.domain.review.persistence.entity.ReviewEntity;
import team.retum.jobis.domain.review.persistence.repository.QuestionJpaRepository;
import team.retum.jobis.domain.review.persistence.repository.ReviewJpaRepository;

@RequiredArgsConstructor
@Component
public class QnAMapper {

    private final ReviewJpaRepository reviewJpaRepository;
    private final QuestionJpaRepository questionJpaRepository;

    public QnAEntity toEntity(QnA domain) {
        ReviewEntity review = reviewJpaRepository.getReferenceById(domain.getReviewId());
        QuestionEntity question = questionJpaRepository.getReferenceById(domain.getQuestionId());

        return QnAEntity.builder()
            .id(domain.getId())
            .question(question)
            .answer(domain.getAnswer())
            .review(review)
            .build();
    }

    public QnA toDomain(QnAEntity entity) {
        return QnA.builder()
            .id(entity.getId())
            .answer(entity.getAnswer())
            .questionId(entity.getQuestion().getId())
            .reviewId(entity.getReview().getId())
            .build();
    }
}
