package team.returm.jobis.domain.code.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.domain.enums.CodeType;

public interface CodeJpaRepository extends JpaRepository<Code, Long> {

    List<Code> queryCodesByIdIn(List<Long> ids);

    @Query("select c from Code c where c.codeType = 'JOB'")
    List<Code> queryJobCodes();

    List<Code> queryCodeByKeywordContainingAndCodeType(String keyword, CodeType codeType);
}
