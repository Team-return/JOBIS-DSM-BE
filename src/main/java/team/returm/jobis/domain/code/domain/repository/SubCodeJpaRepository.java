package team.returm.jobis.domain.code.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.code.domain.SubCode;

import java.util.List;

public interface SubCodeJpaRepository extends JpaRepository<SubCode, Long> {
    List<SubCode> querySubCodesByIdIn(List<Long> ids);
}
