package team.returm.jobis.domain.student.domain.repository;

import org.springframework.data.repository.CrudRepository;
import team.returm.jobis.domain.student.domain.VerifiedStudent;

import java.util.Optional;

public interface VerifiedStudentJpaRepository extends CrudRepository<VerifiedStudent, String> {
    Optional<VerifiedStudent> findByGcnAndName(String gcn, String name);

    void deleteByGcnAndName(String gcn, String name);
}
