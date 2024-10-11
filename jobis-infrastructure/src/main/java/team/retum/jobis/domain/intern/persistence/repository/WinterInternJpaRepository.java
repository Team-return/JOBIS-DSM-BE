package team.retum.jobis.domain.intern.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.intern.persistence.entity.WinterInternEntity;

import java.util.Optional;

public interface WinterInternJpaRepository extends CrudRepository<WinterInternEntity, Long> {
    Optional<WinterInternEntity> findTopByOrderById();

}
