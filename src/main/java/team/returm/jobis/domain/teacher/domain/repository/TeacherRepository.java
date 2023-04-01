package team.returm.jobis.domain.teacher.domain.repository;

import team.returm.jobis.domain.teacher.domain.Teacher;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TeacherRepository {
    private final JPAQueryFactory queryFactory;
    private final TeacherJpaRepository teacherJpaRepository;

    public Optional<Teacher> queryTeacherById(Long teacherId) {
        return teacherJpaRepository.findById(teacherId);
    }
}
