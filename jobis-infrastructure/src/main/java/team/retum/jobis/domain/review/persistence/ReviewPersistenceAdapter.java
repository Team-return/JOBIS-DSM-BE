package team.retum.jobis.domain.review.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.review.dto.ReviewFilter;
import team.retum.jobis.domain.review.exception.ReviewNotFoundException;
import team.retum.jobis.domain.review.model.InterviewLocation;
import team.retum.jobis.domain.review.model.InterviewType;
import team.retum.jobis.domain.review.model.QnA;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.persistence.mapper.QnAMapper;
import team.retum.jobis.domain.review.persistence.mapper.ReviewMapper;
import team.retum.jobis.domain.review.persistence.repository.QnAJpaRepository;
import team.retum.jobis.domain.review.persistence.repository.ReviewJpaRepository;
import team.retum.jobis.domain.review.persistence.repository.vo.QQueryReviewVO;
import team.retum.jobis.domain.review.spi.ReviewPort;
import team.retum.jobis.domain.review.spi.vo.ReviewVO;
import java.util.List;
import static team.retum.jobis.domain.company.persistence.entity.QCompanyEntity.companyEntity;
import static team.retum.jobis.domain.review.persistence.entity.QReviewEntity.reviewEntity;
import static team.retum.jobis.domain.student.persistence.entity.QStudentEntity.studentEntity;
import static team.retum.jobis.domain.code.persistence.entity.QCodeEntity.codeEntity;

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
    public List<ReviewVO> getAllBy(ReviewFilter filter) {
        return queryFactory
            .select(
                new QQueryReviewVO(
                    reviewEntity.id,
                    companyEntity.name,
                    studentEntity.name,
                    reviewEntity.createdAt,
                    codeEntity.keyword
                )
            )
            .from(reviewEntity)
            .join(reviewEntity.student, studentEntity)
            .join(reviewEntity.company, companyEntity)
            .join(reviewEntity.code, codeEntity)
            .where(
                eqInterviewType(filter.getType()),
                eqInterviewLocation(filter.getLocation()),
                eqCompanyId(filter.getCompanyId()),
                eqYear(filter.getYear()),
                eqCode(filter.getCode())
            )
            .limit(filter.getLimit())
            .offset(filter.getOffset())
            .orderBy(reviewEntity.createdAt.desc())
            .fetch().stream()
            .map(ReviewVO.class::cast)
            .toList();
    }

    @Override
    public Long getCountBy(ReviewFilter filter) {
        return queryFactory
            .select(reviewEntity.count())
            .from(reviewEntity)
            .join(reviewEntity.student, studentEntity)
            .join(reviewEntity.company, companyEntity)
            .join(reviewEntity.code, codeEntity)
            .where(
                eqInterviewType(filter.getType()),
                eqInterviewLocation(filter.getLocation()),
                eqCompanyId(filter.getCompanyId()),
                eqYear(filter.getYear()),
                eqCode(filter.getCode())
            )
            .fetchOne();
    }

    //==conditions==//

    private BooleanExpression eqInterviewType(InterviewType type) {
        return type == null ? null : reviewEntity.interviewType.eq(type);
    }

    private BooleanExpression eqInterviewLocation(InterviewLocation location) {
        return location == null ? null : reviewEntity.interviewLocation.eq(location);
    }

    private BooleanExpression eqCompanyId(Long companyId) {
        return companyId == null ? null : reviewEntity.company.id.eq(companyId);
    }

    private BooleanExpression eqYear(Integer year) {
        return year == null ? null : reviewEntity.createdAt.year().eq(year);
    }

    private BooleanExpression eqCode(Long code) {
        return code == null ? null : reviewEntity.code.code.eq(code);
    }
}
