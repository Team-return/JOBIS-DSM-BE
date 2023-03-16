package team.returm.jobis.domain.recruitment.domain.repository;

import team.returm.jobis.domain.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecruitmentJpaRepository extends JpaRepository<Recruitment, UUID> {
}
