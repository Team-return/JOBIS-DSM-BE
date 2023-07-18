package team.retum.jobis.domain.recruitment.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.company.persistence.Company;
import team.retum.jobis.domain.recruitment.persistence.Recruitment;
import team.retum.jobis.domain.recruitment.persistence.enums.RecruitStatus;

import java.util.List;

public interface RecruitmentJpaRepository extends JpaRepository<Recruitment, Long> {
    List<Recruitment> findByIdIn(List<Long> recruitmentIds);

    boolean existsByCompanyAndStatusNot(Company company, RecruitStatus status);
}
