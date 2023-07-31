package team.retum.jobis.domain.recruitment.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;

import java.util.List;

public interface RecruitmentJpaRepository extends JpaRepository<RecruitmentEntity, Long> {
    List<RecruitmentEntity> findByIdIn(List<Long> recruitmentIds);

    boolean existsByCompanyEntityIdAndStatusNot(Long companyId, RecruitStatus status);
}
