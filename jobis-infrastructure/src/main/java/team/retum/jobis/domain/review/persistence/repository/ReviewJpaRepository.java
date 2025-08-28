package team.retum.jobis.domain.review.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.review.persistence.entity.ReviewEntity;

public interface ReviewJpaRepository extends JpaRepository<ReviewEntity, Long> {

    boolean existsByCompanyIdAndStudentId(Long companyId, Long studentId);
}
