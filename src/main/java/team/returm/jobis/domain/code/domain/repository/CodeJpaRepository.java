package team.returm.jobis.domain.code.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.domain.enums.CodeType;

import java.util.List;

public interface CodeJpaRepository extends CrudRepository<Code, Long> {

    List<Code> findCodesByIdIn(List<Long> ids);

    @Query("select c from Code c where c.codeType = 'JOB'")
    List<Code> findJobCodes();

    List<Code> findCodeByKeywordContainingAndCodeType(String keyword, CodeType codeType);

    List<Code> findCodesByKeywordIn(List<String> keywords);
}
