package team.retum.jobis.domain.review.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;
import team.retum.jobis.domain.review.exception.ReviewNotFoundException;
import team.retum.jobis.domain.review.model.QnA;
import team.retum.jobis.domain.review.persistence.entity.QnAEntity;
import team.retum.jobis.domain.review.persistence.entity.ReviewEntity;
import team.retum.jobis.domain.review.persistence.repository.ReviewJpaRepository;

@RequiredArgsConstructor
@Component
public class QnAMapper {

    private final CodeJpaRepository codeJpaRepository;
    private final ReviewJpaRepository reviewJpaRepository;

    public QnAEntity toEntity(QnA domain) {
        ReviewEntity review = reviewJpaRepository.findById(domain.getReviewId())
            .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);
        CodeEntity code = codeJpaRepository.findById(domain.getCodeId())
            .orElseThrow(() -> CodeNotFoundException.EXCEPTION);
        return QnAEntity.builder()
            .id(domain.getId())
            .question(domain.getQuestion())
            .answer(domain.getAnswer())
            .review(review)
            .code(code)
            .build();
    }

    public QnA toDomain(QnAEntity entity) {
        return QnA.builder()
            .id(entity.getId())
            .answer(entity.getAnswer())
            .question(entity.getQuestion())
            .reviewId(entity.getReview().getId())
            .codeId(entity.getCode().getCode())
            .build();
    }
}
