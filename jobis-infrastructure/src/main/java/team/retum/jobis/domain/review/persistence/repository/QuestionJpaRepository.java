package team.retum.jobis.domain.review.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.review.persistence.entity.QuestionEntity;

public interface QuestionJpaRepository extends JpaRepository<QuestionEntity, Long> {
}
