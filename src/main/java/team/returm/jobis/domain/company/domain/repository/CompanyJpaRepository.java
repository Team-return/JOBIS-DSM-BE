package team.returm.jobis.domain.company.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.company.domain.Company;

public interface CompanyJpaRepository extends JpaRepository<Company, Long> {

    boolean existsByBizNo(String bizNo);
}
