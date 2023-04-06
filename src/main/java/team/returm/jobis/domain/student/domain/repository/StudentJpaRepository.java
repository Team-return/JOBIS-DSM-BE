package team.returm.jobis.domain.student.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.student.domain.Student;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    boolean existsByGradeAndClassRoomAndNumber(Integer grade, Integer classRoom, Integer number);
}
