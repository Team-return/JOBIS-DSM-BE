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
import java.time.YearMonth;
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
                eqInterviewPeriod(filter.getYear(), filter.getMonth()),
                containsCompanyName(filter.getCompanyName()),
                eqInterviewType(filter.getInterviewType()),
                eqStudentId(filter.getStudentId())
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

    private BooleanExpression eqInterviewPeriod(Integer year, Integer month) {
        if (year != null && month != null) {
            YearMonth yearMonth = YearMonth.of(year, month);
            return overlapsPeriod(yearMonth.atDay(1), yearMonth.atEndOfMonth());
        }

        if (year != null) {
            return overlapsPeriod(LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 31));
        }

        if (month != null) {
            return overlapsMonth(month);
        }

        return null;
    }

    private BooleanExpression eqInterviewType(ProgressType interviewType) {
        return interviewType == null ? null : interviewEntity.interviewType.eq(interviewType);
    }

    private BooleanExpression eqStudentId(Long studentId) {
        return studentId == null ? null : interviewEntity.student.id.eq(studentId);
    }

    private BooleanExpression overlapsPeriod(LocalDate periodStart, LocalDate periodEnd) {
        BooleanExpression singleDayInterview = interviewEntity.endDate.isNull()
            .and(interviewEntity.startDate.goe(periodStart))
            .and(interviewEntity.startDate.loe(periodEnd));

        BooleanExpression multiDayInterview = interviewEntity.endDate.isNotNull()
            .and(interviewEntity.startDate.loe(periodEnd))
            .and(interviewEntity.endDate.goe(periodStart));

        return singleDayInterview.or(multiDayInterview);
    }

    private BooleanExpression overlapsMonth(Integer month) {
        BooleanExpression singleDayInterview = interviewEntity.endDate.isNull()
            .and(interviewEntity.startDate.month().eq(month));

        BooleanExpression sameYearMultiDayInterview = interviewEntity.endDate.isNotNull()
            .and(interviewEntity.startDate.year().eq(interviewEntity.endDate.year()))
            .and(interviewEntity.startDate.month().loe(month))
            .and(interviewEntity.endDate.month().goe(month));

        BooleanExpression crossYearAdjacentInterview = interviewEntity.endDate.isNotNull()
            .and(interviewEntity.endDate.year().subtract(interviewEntity.startDate.year()).eq(1))
            .and(
                interviewEntity.startDate.month().loe(month)
                    .or(interviewEntity.endDate.month().goe(month))
            );

        BooleanExpression crossYearLongInterview = interviewEntity.endDate.isNotNull()
            .and(interviewEntity.endDate.year().subtract(interviewEntity.startDate.year()).gt(1));

        return singleDayInterview
            .or(sameYearMultiDayInterview)
            .or(crossYearAdjacentInterview)
            .or(crossYearLongInterview);
    }
}
