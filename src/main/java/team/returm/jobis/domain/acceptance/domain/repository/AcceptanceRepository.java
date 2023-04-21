package team.returm.jobis.domain.acceptance.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.acceptance.domain.Acceptance;
import java.time.LocalDate;
import java.util.List;

import static team.returm.jobis.domain.acceptance.domain.QAcceptance.acceptance;

@RequiredArgsConstructor
@Repository
public class AcceptanceRepository {

    private final AcceptanceJpaRepository acceptanceJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public List<Acceptance> queryAcceptancesByCompanyIdAndYear(Long companyId, Integer year) {
        return acceptanceJpaRepository.findByCompanyIdAndYear(companyId, year);
    }

    public void updateContractDate(LocalDate contractDate, List<Long> acceptanceIds) {
        jpaQueryFactory
                .update(acceptance)
                .set(acceptance.contractDate, contractDate)
                .where(acceptance.id.in(acceptanceIds))
                .execute();
    }

    public void saveAllAcceptance(List<Acceptance> acceptances) {
        acceptanceJpaRepository.saveAll(acceptances);
    }
}
