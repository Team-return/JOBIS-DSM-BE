package team.retum.jobis.domain.student.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;

public interface StudentJpaRepository extends JpaRepository<StudentEntity, Long> {

    boolean existsByGradeAndClassRoomAndNumberAndEntranceYear(Integer grade, Integer classRoom, Integer number, Integer entranceYear);

    boolean existsByGradeAndClassRoomAndNumberAndName(Integer grade, Integer classRoom, Integer number, String name);

    int countByGradeAndEntranceYear(int grade, int entranceYear);

}
