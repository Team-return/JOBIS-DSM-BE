package team.retum.jobis.domain.student.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.student.persistence.Student;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    boolean existsByGradeAndClassRoomAndNumber(Integer grade, Integer classRoom, Integer number);

}
