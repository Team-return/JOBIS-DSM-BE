package team.retum.jobis.domain.interview.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.interview.persistence.entity.DocumentNumberEntity;

public interface DocumentNumberJpaRepository extends JpaRepository<DocumentNumberEntity, Long> {

}

