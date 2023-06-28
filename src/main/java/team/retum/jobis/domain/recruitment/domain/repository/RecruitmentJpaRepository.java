package team.retum.jobis.domain.recruitment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.company.domain.Company;
import team.retum.jobis.domain.recruitment.domain.Recruitment;
import team.retum.jobis.domain.recruitment.domain.enums.RecruitStatus;

import java.util.List;

public interface RecruitmentJpaRepository extends JpaRepository<Recruitment, Long> {
    List<Recruitment> findByIdIn(List<Long> recruitmentIds);

    boolean existsByCompanyAndStatusNot(Company company, RecruitStatus status);
}
