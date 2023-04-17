package team.returm.jobis.domain.acceptance.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.acceptance.domain.Acceptance;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class AcceptanceRepository {

    private final AcceptanceJpaRepository acceptanceJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public List<Acceptance> queryAcceptancesByCompanyIdAndYear(Long companyId, Integer year) {
        return acceptanceJpaRepository.queryByCompanyIdAndYear(companyId, year);
    }
}
