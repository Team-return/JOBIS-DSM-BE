package team.retum.jobis.domain.review.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.review.persistence.entity.QuestionEntity;

@Repository
public interface QuestionJpaRepository extends JpaRepository<QuestionEntity, Long> {
}
