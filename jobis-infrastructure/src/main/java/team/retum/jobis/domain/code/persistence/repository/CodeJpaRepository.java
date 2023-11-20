package team.retum.jobis.domain.code.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;

import java.util.List;

public interface CodeJpaRepository extends CrudRepository<CodeEntity, Long> {

    List<CodeEntity> findCodesByCodeIn(List<Long> ids);
}
