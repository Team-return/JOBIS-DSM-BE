package team.retum.jobis.domain.student.domain.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.student.domain.VerifiedStudent;

import java.util.Optional;

public interface VerifiedStudentJpaRepository extends CrudRepository<VerifiedStudent, String> {
    Optional<VerifiedStudent> findByGcnAndName(String gcn, String name);

    boolean existsByGcnAndName(String gcn, String name);

    void deleteByGcnAndName(String gcn, String name);
}
