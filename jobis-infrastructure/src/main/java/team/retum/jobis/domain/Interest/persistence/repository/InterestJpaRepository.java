package team.retum.jobis.domain.Interest.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.Interest.persistence.entity.InterestEntity;

import java.util.Optional;

public interface InterestJpaRepository extends CrudRepository<InterestEntity, Long> {

    Optional<InterestEntity> findByStudentIdAndCode(Long studentId, Long code);
}
