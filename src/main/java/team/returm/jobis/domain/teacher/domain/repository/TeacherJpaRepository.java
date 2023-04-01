package team.returm.jobis.domain.teacher.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.teacher.domain.Teacher;

public interface TeacherJpaRepository extends JpaRepository<Teacher, Long> {
}
