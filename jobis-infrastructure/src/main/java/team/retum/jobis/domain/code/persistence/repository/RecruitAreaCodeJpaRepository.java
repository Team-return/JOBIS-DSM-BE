package team.retum.jobis.domain.code.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.code.persistence.entity.RecruitAreaCodeEntity;
import team.retum.jobis.domain.code.persistence.entity.RecruitAreaCodeId;

import java.util.List;

public interface RecruitAreaCodeJpaRepository extends JpaRepository<RecruitAreaCodeEntity, RecruitAreaCodeId> {
    List<RecruitAreaCodeEntity> findByRecruitAreaId(Long id);
}
