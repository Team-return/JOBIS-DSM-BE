package team.retum.jobis.domain.student.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.student.persistence.StudentEntity;

public interface StudentJpaRepository extends JpaRepository<StudentEntity, Long> {
    boolean existsByGradeAndClassRoomAndNumber(Integer grade, Integer classRoom, Integer number);

}
