package team.returm.jobis.domain.acceptance.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AcceptanceRepository {

    private final AcceptanceJpaRepository acceptanceJpaRepository;
}
