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
import team.retum.jobis.domain.review.persistence.repository.vo.QQueryMyReviewVO;
import team.retum.jobis.domain.review.persistence.repository.vo.QQueryQnAVO;
import team.retum.jobis.domain.review.persistence.repository.vo.QQueryReviewDetailVO;
import team.retum.jobis.domain.review.persistence.repository.vo.QQueryReviewVO;
import team.retum.jobis.domain.review.spi.ReviewPort;
import team.retum.jobis.domain.review.spi.vo.MyReviewVO;
import team.retum.jobis.domain.review.spi.vo.QnAVO;
import team.retum.jobis.domain.review.spi.vo.ReviewDetailVO;
import team.retum.jobis.domain.review.spi.vo.ReviewVO;

import java.util.List;
import java.util.Optional;

import static team.retum.jobis.domain.company.persistence.entity.QCompanyEntity.companyEntity;
import static team.retum.jobis.domain.review.persistence.entity.QQnAEntity.qnAEntity;
import static team.retum.jobis.domain.review.persistence.entity.QQuestionEntity.questionEntity;
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
    public List<ReviewVO> getAllByFilter(ReviewFilter filter) {
        return queryFactory
            .select(
                new QQueryReviewVO(
                    reviewEntity.id,
                    companyEntity.name,
                    companyEntity.companyLogoUrl,
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
                eqYears(filter.getYears()),
                eqCode(filter.getCode()),
                containsKeyword(filter.getKeyword())
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
                eqYears(filter.getYears()),
                eqCode(filter.getCode()),
                containsKeyword(filter.getKeyword())
            )
            .fetchOne();
    }

    @Override
    public Optional<ReviewDetailVO> getDetailById(Long reviewId) {
        return Optional.ofNullable(
            queryFactory
                .select(
                    new QQueryReviewDetailVO(
                        reviewEntity.id,
                        companyEntity.name,
                        studentEntity.name,
                        reviewEntity.createdAt.year(),
                        codeEntity.keyword,
                        reviewEntity.interviewType,
                        reviewEntity.interviewLocation,
                        reviewEntity.interviewerCount,
                        reviewEntity.question,
                        reviewEntity.answer
                    )
                )
                .from(reviewEntity)
                .join(reviewEntity.student, studentEntity)
                .join(reviewEntity.company, companyEntity)
                .join(reviewEntity.code, codeEntity)
                .where(eqReviewId(reviewId))
                .fetchOne()
        );
    }

    @Override
    public List<QnAVO> getQnAVOById(Long reviewId) {
        return queryFactory
            .select(
                new QQueryQnAVO(
                    qnAEntity.id,
                    questionEntity.question,
                    qnAEntity.answer
                )
            )
            .from(qnAEntity)
            .join(qnAEntity.question, questionEntity)
            .where(qnAEntity.review.id.eq(reviewId))
            .fetch().stream()
            .map(QnAVO.class::cast)
            .toList();
    }

    @Override
    public boolean existsById(Long reviewId) {
        return reviewJpaRepository.existsById(reviewId);
    }

    @Override
    public List<MyReviewVO> getMyReviewsById(Long studentId) {
        return queryFactory
            .select(
                new QQueryMyReviewVO(
                    reviewEntity.id,
                    companyEntity.name
                )
            )
            .from(reviewEntity)
            .join(reviewEntity.company, companyEntity)
            .where(reviewEntity.student.id.eq(studentId))
            .fetch().stream()
            .map(MyReviewVO.class::cast)
            .toList();
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

    private BooleanExpression eqYears(List<Integer> years) {
        return years == null ? null : reviewEntity.createdAt.year().in(years);
    }

    private BooleanExpression eqCode(Long code) {
        return code == null ? null : reviewEntity.code.code.eq(code);
    }

    private BooleanExpression eqReviewId(Long reviewId) {
        return reviewId == null ? null : reviewEntity.id.eq(reviewId);
    }

    private BooleanExpression containsKeyword(String keyword) {
        return keyword == null ? null : companyEntity.name.contains(keyword).or(studentEntity.name.contains(keyword));
    }
}
