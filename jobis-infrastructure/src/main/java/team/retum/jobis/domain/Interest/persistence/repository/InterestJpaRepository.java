package team.retum.jobis.domain.interest.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.interest.persistence.entity.InterestEntity;

import java.util.List;
import java.util.Optional;

public interface InterestJpaRepository extends CrudRepository<InterestEntity, Long> {

    Optional<InterestEntity> findByStudentIdAndCode(Long studentId, Long code);

    List<InterestEntity> findByStudentId(Long studentId);
}
