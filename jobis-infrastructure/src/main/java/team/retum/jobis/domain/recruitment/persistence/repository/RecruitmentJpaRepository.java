package team.retum.jobis.domain.recruitment.persistence.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface RecruitmentJpaRepository extends JpaRepository<RecruitmentEntity, Long> {

    List<RecruitmentEntity> findByIdIn(List<Long> recruitmentIds);

    boolean existsByCompanyIdAndStatusNotAndWinterIntern(Long companyId, RecruitStatus status, boolean winterIntern);

    @Query("SELECT r FROM RecruitmentEntity r WHERE r.createdAt BETWEEN :startDate AND :endDate")
    List<Recruitment> findByCreationDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
