package team.retum.jobis.domain.acceptance.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.acceptance.domain.Acceptance;

import java.util.List;

public interface AcceptanceJpaRepository extends JpaRepository<Acceptance, Long> {
    List<Acceptance> findByCompanyIdAndYear(Long companyId, Integer year);
}
