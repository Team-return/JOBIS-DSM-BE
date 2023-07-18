package team.retum.jobis.domain.code.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.code.persistence.RecruitAreaCode;
import team.retum.jobis.domain.code.persistence.RecruitAreaCodeId;

public interface RecruitAreaCodeJpaRepository extends JpaRepository<RecruitAreaCode, RecruitAreaCodeId> {
    void deleteAllByRecruitAreaId(Long recruitAreaId);
}
