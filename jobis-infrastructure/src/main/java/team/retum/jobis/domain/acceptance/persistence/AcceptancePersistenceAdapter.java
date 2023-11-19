package team.retum.jobis.domain.acceptance.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.acceptance.model.Acceptance;
import team.retum.jobis.domain.acceptance.persistence.mapper.AcceptanceMapper;
import team.retum.jobis.domain.acceptance.persistence.repository.AcceptanceJpaRepository;
import team.retum.jobis.domain.acceptance.persistence.repository.vo.QQueryAcceptanceVO;
import team.retum.jobis.domain.acceptance.spi.AcceptancePort;
import team.retum.jobis.domain.acceptance.spi.vo.AcceptanceVO;

import java.time.LocalDate;
import java.util.List;

import static team.retum.jobis.domain.acceptance.persistence.entity.QAcceptanceEntity.acceptanceEntity;
import static team.retum.jobis.domain.student.persistence.entity.QStudentEntity.studentEntity;

@RequiredArgsConstructor
@Repository
public class AcceptancePersistenceAdapter implements AcceptancePort {

    private final AcceptanceJpaRepository acceptanceJpaRepository;
    private final AcceptanceMapper acceptanceMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AcceptanceVO> queryAcceptancesByCompanyIdAndYear(Long companyId, Integer year) {
        return queryFactory
                .select(
                        new QQueryAcceptanceVO(
                                acceptanceEntity.id,
                                studentEntity.grade,
                                studentEntity.classRoom,
                                studentEntity.number,
                                studentEntity.name,
                                acceptanceEntity.contractDate
                        )
                )
                .from(acceptanceEntity)
                .join(acceptanceEntity.student, studentEntity)
                .where(
                        acceptanceEntity.company.id.eq(companyId),
                        acceptanceEntity.year.eq(year)
                ).fetch().stream()
                .map(AcceptanceVO.class::cast)
                .toList();
    }

    @Override
    public void updateContractDate(LocalDate contractDate, List<Long> acceptanceIds) {
        queryFactory
                .update(acceptanceEntity)
                .set(acceptanceEntity.contractDate, contractDate)
                .where(acceptanceEntity.id.in(acceptanceIds))
                .execute();
    }

    @Override
    public void saveAllAcceptance(List<Acceptance> acceptances) {
        acceptanceJpaRepository.saveAll(
                acceptances.stream()
                        .map(acceptanceMapper::toEntity)
                        .toList()
        );
    }
}
