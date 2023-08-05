package team.retum.jobis.domain.acceptance.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.acceptance.persistence.entity.AcceptanceEntity;

import java.util.List;

public interface AcceptanceJpaRepository extends JpaRepository<AcceptanceEntity, Long> {
    List<AcceptanceEntity> findByCompanyIdAndYear(Long companyId, Integer year);
}
