package team.retum.jobis.domain.recruitment.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import com.example.jobisapplication.domain.recruitment.domain.RecruitStatus;

import java.util.List;

public interface RecruitmentJpaRepository extends JpaRepository<RecruitmentEntity, Long> {
    List<RecruitmentEntity> findByIdIn(List<Long> recruitmentIds);

    boolean existsByCompanyAndStatusNot(CompanyEntity companyEntity, RecruitStatus status);
}
