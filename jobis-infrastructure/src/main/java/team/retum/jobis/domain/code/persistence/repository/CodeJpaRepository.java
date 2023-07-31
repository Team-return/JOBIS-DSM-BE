package team.retum.jobis.domain.code.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.code.model.CodeType;

import java.util.List;

public interface CodeJpaRepository extends CrudRepository<CodeEntity, Long> {

    List<CodeEntity> findCodesByIdIn(List<Long> ids);
}
