package team.retum.jobis.domain.interview.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.interview.dto.InterviewFilter;
import team.retum.jobis.domain.interview.model.Interview;
import team.retum.jobis.domain.interview.persistence.mapper.InterviewMapper;
import team.retum.jobis.domain.interview.persistence.repository.InterviewJpaRepository;
import team.retum.jobis.domain.interview.spi.InterviewPort;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;
import java.util.List;

import static team.retum.jobis.domain.interview.persistence.entity.QInterviewEntity.interviewEntity;

@Repository
@RequiredArgsConstructor
public class InterviewPersistenceAdapter implements InterviewPort {

    private final JPAQueryFactory queryFactory;
    private final InterviewMapper interviewMapper;
    private final InterviewJpaRepository interviewJpaRepository;

    @Override
    public Interview save(Interview interview) {
        return interviewMapper.toDomain(
            interviewJpaRepository.save(
                interviewMapper.toEntity(interview)
            )
        );
    }

    @Override
    public List<Interview> getInterviewsBy(InterviewFilter filter) {
        return queryFactory
            .selectFrom(interviewEntity)
            .where(
                eqInterviewYear(filter.getYear()),
                eqInterviewMonth(filter.getMonth()),
                containsCompanyName(filter.getCompanyName()),
                eqInterviewType(filter.getInterviewType())
            )
            .orderBy(interviewEntity.startDate.asc())
            .fetch().stream()
            .map(interviewMapper::toDomain)
            .toList();
    }

    @Override
    public List<Interview> getByIds(List<Long> interviewIds) {
        return queryFactory
            .selectFrom(interviewEntity)
            .where(interviewEntity.id.in(interviewIds))
            .fetch()
            .stream()
            .map(interviewMapper::toDomain)
            .toList();
    }

    @Override
    public List<Interview> getByDocumentNumberId(Long documentNumberId) {
        return queryFactory
            .selectFrom(interviewEntity)
            .where(interviewEntity.documentNumber.id.eq(documentNumberId))
            .fetch()
            .stream()
            .map(interviewMapper::toDomain)
            .toList();
    }

    @Override
    public List<Interview> getInterviewsByDateRange(LocalDate targetDate) {
        return queryFactory
            .selectFrom(interviewEntity)
            .where(isInterviewOnDate(targetDate))
            .fetch()
            .stream()
            .map(interviewMapper::toDomain)
            .toList();
    }

    //==conditions==//

    private BooleanExpression isInterviewOnDate(LocalDate targetDate) {
        BooleanExpression singleDayInterview = interviewEntity.endDate.isNull()
            .and(interviewEntity.startDate.eq(targetDate));

        BooleanExpression multiDayInterview = interviewEntity.endDate.isNotNull()
            .and(interviewEntity.startDate.loe(targetDate))
            .and(interviewEntity.endDate.goe(targetDate));

        return singleDayInterview.or(multiDayInterview);
    }

    private BooleanExpression containsCompanyName(String companyName) {
        if (companyName == null || companyName.isBlank()) {
            return null;
        }

        return interviewEntity.companyName.contains(companyName);
    }

    private BooleanExpression eqInterviewYear(Integer year) {
        return year == null ? null : interviewEntity.startDate.year().eq(year);
    }

    private BooleanExpression eqInterviewMonth(Integer month) {
        return month == null ? null : interviewEntity.startDate.month().eq(month);
    }

    private BooleanExpression eqInterviewType(ProgressType interviewType) {
        return interviewType == null ? null : interviewEntity.interviewType.eq(interviewType);
    }
}
