package team.retum.jobis.domain.acceptance.persistence;

import com.example.jobisapplication.domain.acceptance.model.Acceptance;
import com.example.jobisapplication.domain.acceptance.spi.AcceptancePort;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.acceptance.persistence.mapper.AcceptanceMapper;
import team.retum.jobis.domain.acceptance.persistence.repository.AcceptanceJpaRepository;

import java.time.LocalDate;
import java.util.List;

import static team.retum.jobis.domain.acceptance.persistence.entity.QAcceptanceEntity.acceptanceEntity;

@RequiredArgsConstructor
@Repository
public class AcceptancePersistenceAdapter implements AcceptancePort {

    private final AcceptanceJpaRepository acceptanceJpaRepository;
    private final AcceptanceMapper acceptanceMapper;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Acceptance> queryAcceptancesByCompanyIdAndYear(Long companyId, Integer year) {
        return acceptanceJpaRepository.findByCompanyIdAndYear(companyId, year).stream()
                .map(acceptanceMapper::toDomain)
                .toList();
    }

    @Override
    public void updateContractDate(LocalDate contractDate, List<Long> acceptanceIds) {
        jpaQueryFactory
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
