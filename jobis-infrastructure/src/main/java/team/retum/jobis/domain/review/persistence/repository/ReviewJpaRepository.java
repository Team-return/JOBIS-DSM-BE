package team.retum.jobis.domain.review.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.review.persistence.entity.ReviewEntity;


public interface ReviewJpaRepository extends CrudRepository<ReviewEntity, Long> {

    boolean existsByCompanyIdAndStudentName(Long companyId, String studentName);
}
