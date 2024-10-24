package team.retum.jobis.domain.review.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.review.exception.ReviewNotFoundException;
import team.retum.jobis.domain.review.model.QnA;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.persistence.mapper.QnAMapper;
import team.retum.jobis.domain.review.persistence.mapper.ReviewMapper;
import team.retum.jobis.domain.review.persistence.repository.QnAJpaRepository;
import team.retum.jobis.domain.review.persistence.repository.ReviewJpaRepository;
import team.retum.jobis.domain.review.persistence.repository.vo.QQueryQnAVO;
import team.retum.jobis.domain.review.persistence.repository.vo.QQueryReviewVO;
import team.retum.jobis.domain.review.spi.ReviewPort;
import team.retum.jobis.domain.review.spi.vo.QnAVO;
import team.retum.jobis.domain.review.spi.vo.ReviewVO;

import java.util.List;

import static team.retum.jobis.domain.code.persistence.entity.QCodeEntity.codeEntity;
import static team.retum.jobis.domain.company.persistence.entity.QCompanyEntity.companyEntity;
import static team.retum.jobis.domain.review.persistence.entity.QQnAEntity.qnAEntity;
import static team.retum.jobis.domain.review.persistence.entity.QReviewEntity.reviewEntity;
import static team.retum.jobis.domain.student.persistence.entity.QStudentEntity.studentEntity;

@RequiredArgsConstructor
@Repository
public class ReviewPersistenceAdapter implements ReviewPort {

    private final ReviewJpaRepository reviewJpaRepository;
    private final ReviewMapper reviewMapper;
    private final QnAJpaRepository qnAJpaRepository;
    private final QnAMapper qnAMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public Review save(Review review) {
        return reviewMapper.toDomain(
            reviewJpaRepository.save(
                reviewMapper.toEntity(review)
            )
        );
    }

    @Override
    public void saveAll(List<QnA> qnAs) {
        qnAJpaRepository.saveAll(
            qnAs.stream()
                .map(qnAMapper::toEntity)
                .toList()
        );
    }

    @Override
    public void delete(Review review) {
        reviewJpaRepository.delete(reviewMapper.toEntity(review));
    }

    @Override
    public boolean existsByCompanyIdAndStudentId(Long companyId, Long studentId) {
        return reviewJpaRepository.existsByCompanyIdAndStudentId(companyId, studentId);
    }

    @Override
    public Review getByIdOrThrow(Long reviewId) {
        return reviewJpaRepository.findById(reviewId)
            .map(reviewMapper::toDomain)
            .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

    }

    @Override
    public List<QnAVO> getAllQnAsByReviewId(Long reviewId) {
        return queryFactory
            .select(
                new QQueryQnAVO(
                    qnAEntity.question,
                    qnAEntity.answer,
                    codeEntity.keyword
                )
            )
            .from(qnAEntity)
            .join(qnAEntity.code, codeEntity)
            .where(qnAEntity.review.id.eq(reviewId))
            .fetch().stream()
            .map(QnAVO.class::cast)
            .toList();
    }

    @Override
    public List<ReviewVO> getAllByCompanyId(Long companyId) {
        return queryFactory
            .select(
                new QQueryReviewVO(
                    reviewEntity.id,
                    studentEntity.name,
                    reviewEntity.createdAt
                )
            )
            .from(reviewEntity)
            .join(reviewEntity.student, studentEntity)
            .join(reviewEntity.company, companyEntity)
            .where(companyEntity.id.eq(companyId))
            .fetch().stream()
            .map(ReviewVO.class::cast)
            .toList();
    }
}
