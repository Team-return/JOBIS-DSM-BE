package team.returm.jobis.domain.code.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.code.domain.Code;

public interface CodeJpaRepository extends JpaRepository<Code, Long> {
    List<Code> queryCodeByKeywordContaining(String keyword);

    List<Code> queryCodesByIdIn(List<Long> ids);
}
