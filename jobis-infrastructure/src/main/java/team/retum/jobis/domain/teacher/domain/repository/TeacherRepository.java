package team.retum.jobis.domain.teacher.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.teacher.domain.Teacher;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TeacherRepository {
    private final JPAQueryFactory queryFactory;
    private final TeacherJpaRepository teacherJpaRepository;

    public Optional<Teacher> queryTeacherById(Long teacherId) {
        return teacherJpaRepository.findById(teacherId);
    }
}
