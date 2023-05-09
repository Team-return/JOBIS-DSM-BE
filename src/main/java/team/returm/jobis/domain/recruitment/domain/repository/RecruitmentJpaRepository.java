package team.returm.jobis.domain.recruitment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.recruitment.domain.Recruitment;

import java.util.List;

public interface RecruitmentJpaRepository extends JpaRepository<Recruitment, Long> {
    List<Recruitment> findByIdIn(List<Long> recruitmentIds);
}
