package team.retum.jobis.domain.student.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.student.persistence.entity.VerifiedStudentEntity;

import java.util.Optional;

public interface VerifiedStudentJpaRepository extends CrudRepository<VerifiedStudentEntity, String> {

    boolean existsByGcnAndName(String gcn, String name);

    void deleteByGcnAndName(String gcn, String name);
}
