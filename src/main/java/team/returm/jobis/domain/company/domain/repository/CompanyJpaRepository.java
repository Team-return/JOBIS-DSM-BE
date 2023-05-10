package team.returm.jobis.domain.company.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.company.domain.Company;

import java.util.List;

public interface CompanyJpaRepository extends JpaRepository<Company, Long> {

    boolean existsByBizNo(String bizNo);

    List<Company> findAllByIdIn(List<Long> companyIds);

    boolean existsById(Long id);
}
