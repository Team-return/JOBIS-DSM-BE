package team.retum.jobis.domain.teacher.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.teacher.model.Teacher;
import team.retum.jobis.domain.teacher.persistence.entity.TeacherEntity;
import team.retum.jobis.domain.user.exception.UserNotFoundException;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import team.retum.jobis.domain.user.persistence.repository.UserJpaRepository;

@Component
@RequiredArgsConstructor
public class TeacherMapper {

    private final UserJpaRepository userJpaRepository;

    public TeacherEntity toEntity(Teacher domain) {
        UserEntity user = userJpaRepository.findById(domain.getId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return new TeacherEntity(domain.getId(), user);
    }

    public Teacher toDomain(TeacherEntity entity) {
        return Teacher.builder()
                .id(entity.getId())
                .build();
    }
}
