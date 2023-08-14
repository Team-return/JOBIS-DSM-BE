package team.retum.jobis.domain.review.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.review.persistence.entity.QnAEntity;

public interface QnAJpaRepository extends CrudRepository<QnAEntity, Long> {
}
