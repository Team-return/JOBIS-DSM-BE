package team.returm.jobis.domain.company.domain.repository;

import team.returm.jobis.domain.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyJpaRepository extends JpaRepository<Company, Long> {

    boolean existsByBizNo(String bizNo);
}
