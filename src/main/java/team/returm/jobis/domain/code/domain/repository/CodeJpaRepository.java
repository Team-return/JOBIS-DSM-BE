package team.returm.jobis.domain.code.domain.repository;

import team.returm.jobis.domain.code.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CodeJpaRepository extends JpaRepository<Code, Long> {
    List<Code> queryCodeByKeywordContaining(String keyword);

    @Query("select c from Code c where c.codeType = 'JOB'")
    List<Code> queryJobCodes();
}
