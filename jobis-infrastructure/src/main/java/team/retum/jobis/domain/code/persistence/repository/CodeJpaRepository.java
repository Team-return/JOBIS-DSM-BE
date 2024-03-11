package team.retum.jobis.domain.code.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;

import java.util.List;
import java.util.Optional;

public interface CodeJpaRepository extends CrudRepository<CodeEntity, Long> {

    List<CodeEntity> findCodesByCodeIn(List<Long> codes);
    Optional<CodeEntity> findByKeywordAndType(String keyword, CodeType type);
}
