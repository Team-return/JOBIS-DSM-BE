package team.returm.jobis.domain.recruitment.domain.repository;

import java.util.List;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecruitmentJpaRepository extends JpaRepository<Recruitment, UUID> {
    List<Recruitment> findByIdIn(List<UUID> recruitmentIds);
}
