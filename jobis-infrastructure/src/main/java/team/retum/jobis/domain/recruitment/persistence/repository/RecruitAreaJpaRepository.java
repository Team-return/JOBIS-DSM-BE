package team.retum.jobis.domain.recruitment.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitAreaEntity;

public interface RecruitAreaJpaRepository extends JpaRepository<RecruitAreaEntity, Long> {
    void deleteByRecruitmentId(Long recruitment);
}
