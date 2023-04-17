package team.returm.jobis.domain.acceptance.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.acceptance.domain.Acceptance;

import java.util.List;

public interface AcceptanceJpaRepository extends JpaRepository<Acceptance, Long> {
    List<Acceptance> queryByCompanyId(Long companyId);
}
