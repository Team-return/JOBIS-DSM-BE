package team.retum.jobis.domain.interview.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.interview.persistence.entity.InterviewEntity;

public interface InterviewJpaRepository extends JpaRepository<InterviewEntity, Long> {

}
