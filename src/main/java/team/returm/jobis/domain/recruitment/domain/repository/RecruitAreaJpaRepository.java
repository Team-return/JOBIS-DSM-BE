package team.returm.jobis.domain.recruitment.domain.repository;

import team.returm.jobis.domain.recruitment.domain.RecruitArea;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RecruitAreaJpaRepository extends JpaRepository<RecruitArea, UUID> {
}
