package team.returm.jobis.domain.code.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.code.domain.RecruitAreaCode;
import team.returm.jobis.domain.code.domain.RecruitAreaCodeId;

public interface RecruitAreaCodeJpaRepository extends JpaRepository<RecruitAreaCode, RecruitAreaCodeId> {
    void deleteAllByRecruitAreaId(Long recruitAreaId);
}
