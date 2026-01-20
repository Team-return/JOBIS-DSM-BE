package team.retum.jobis.domain.interview.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.interview.model.Interview;
import team.retum.jobis.domain.interview.persistence.mapper.InterviewMapper;
import team.retum.jobis.domain.interview.persistence.repository.InterviewJpaRepository;
import team.retum.jobis.domain.interview.persistence.repository.vo.QQueryInterviewVO;
import team.retum.jobis.domain.interview.spi.InterviewPort;
import team.retum.jobis.domain.interview.spi.vo.InterviewVO;

import java.util.List;

import static team.retum.jobis.domain.interview.persistence.entity.QInterviewEntity.interviewEntity;

@Repository
@RequiredArgsConstructor
public class InterviewPersistenceAdapter implements InterviewPort {

    private final InterviewMapper interviewMapper;
    private final InterviewJpaRepository interviewJpaRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public Interview save(Interview interview) {
        return interviewMapper.toDomain(
            interviewJpaRepository.save(
                interviewMapper.toEntity(interview)
            )
        );
    }

    @Override
    public List<InterviewVO> getInterviewsByMonth(Integer year, Integer month) {
        return queryFactory
            .select(
                new QQueryInterviewVO(
                    interviewEntity.id,
                    interviewEntity.interviewType,
                    interviewEntity.startDate,
                    interviewEntity.endDate,
                    interviewEntity.interviewTime,
                    interviewEntity.companyName,
                    interviewEntity.location
                )
            )
            .from(interviewEntity)
            .where(
                interviewEntity.startDate.year().eq(year),
                interviewEntity.startDate.month().eq(month)
            )
            .orderBy(interviewEntity.startDate.asc())
            .fetch().stream()
            .map(InterviewVO.class::cast)
            .toList();
    }

    @Override
    public List<InterviewVO> getAllInterviews() {
        return queryFactory
            .select(
                new QQueryInterviewVO(
                    interviewEntity.id,
                    interviewEntity.interviewType,
                    interviewEntity.startDate,
                    interviewEntity.endDate,
                    interviewEntity.interviewTime,
                    interviewEntity.companyName,
                    interviewEntity.location
                )
            )
            .from(interviewEntity)
            .orderBy(interviewEntity.startDate.asc())
            .fetch().stream()
            .map(InterviewVO.class::cast)
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
}
