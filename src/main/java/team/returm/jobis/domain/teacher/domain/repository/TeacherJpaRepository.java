package team.returm.jobis.domain.teacher.domain.repository;

import team.returm.jobis.domain.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeacherJpaRepository extends JpaRepository<Teacher, Long> {
}
