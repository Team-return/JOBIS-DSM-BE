package team.retum.jobis.domain.code.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import com.example.jobisapplication.domain.code.model.CodeType;

import java.util.List;

public interface CodeJpaRepository extends CrudRepository<CodeEntity, Long> {

    List<CodeEntity> findCodesByIdIn(List<Long> ids);

    @Query("select c from CodeEntity c where c.codeType = 'JOB'")
    List<CodeEntity> findJobCodes();

    List<CodeEntity> findCodeByKeywordContainingAndCodeType(String keyword, CodeType codeType);

    List<CodeEntity> findCodesByKeywordIn(List<String> keywords);
}
