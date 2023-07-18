package team.retum.jobis.domain.company.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.company.persistence.CompanyEntity;

import java.util.List;

public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, Long> {

    boolean existsByBizNo(String bizNo);

    List<CompanyEntity> findAllByIdIn(List<Long> companyIds);

    boolean existsById(Long companyId);
}
