package team.retum.jobis.domain.acceptance.persistence.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.acceptance.persistence.AcceptanceEntity;

import java.time.LocalDate;
import java.util.List;

import static team.retum.jobis.domain.acceptance.persistence.QAcceptance.acceptance;

@RequiredArgsConstructor
@Repository
public class AcceptanceRepository {

    private final AcceptanceJpaRepository acceptanceJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public List<AcceptanceEntity> queryAcceptancesByCompanyIdAndYear(Long companyId, Integer year) {
        return acceptanceJpaRepository.findByCompanyIdAndYear(companyId, year);
    }

    public void updateContractDate(LocalDate contractDate, List<Long> acceptanceIds) {
        jpaQueryFactory
                .update(acceptance)
                .set(acceptance.contractDate, contractDate)
                .where(acceptance.id.in(acceptanceIds))
                .execute();
    }

    public void saveAllAcceptance(List<AcceptanceEntity> acceptanceEntities) {
        acceptanceJpaRepository.saveAll(acceptanceEntities);
    }
}
