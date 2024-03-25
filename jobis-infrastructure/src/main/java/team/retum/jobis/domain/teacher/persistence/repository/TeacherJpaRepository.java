package team.retum.jobis.domain.teacher.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.teacher.persistence.entity.TeacherEntity;

public interface TeacherJpaRepository extends JpaRepository<TeacherEntity, Long> {

}
