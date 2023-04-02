package team.returm.jobis.domain.teacher.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.teacher.domain.Teacher;

@Repository
@RequiredArgsConstructor
public class TeacherRepository {
    private final JPAQueryFactory queryFactory;
    private final TeacherJpaRepository teacherJpaRepository;

    public Optional<Teacher> queryTeacherById(Long teacherId) {
        return teacherJpaRepository.findById(teacherId);
    }
}
