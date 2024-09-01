package team.retum.jobis.domain.wintern.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.wintern.persistence.entity.WinternEntity;

import java.util.Optional;

public interface WinternJpaRepository extends CrudRepository<WinternEntity, Long> {
    Optional<WinternEntity> findTopByOrderById();

}
