package team.returm.jobis.domain.student.domain.repository;

import org.springframework.data.repository.CrudRepository;
import team.returm.jobis.domain.student.domain.VerifiedStudent;

public interface VerifiedStudentJpaRepository extends CrudRepository<VerifiedStudent, String> {
}
