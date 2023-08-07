package team.retum.jobis.domain.student.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.user.exception.UserNotFoundException;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import team.retum.jobis.domain.user.persistence.repository.UserJpaRepository;

@RequiredArgsConstructor
@Component
public class StudentMapper {

    private final UserJpaRepository userJpaRepository;

    public StudentEntity toEntity(Student domain) {
        UserEntity user = userJpaRepository.findById(domain.getId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return StudentEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .grade(domain.getGrade())
                .classRoom(domain.getClassRoom())
                .department(domain.getDepartment())
                .profileImageUrl(domain.getProfileImageUrl())
                .number(domain.getNumber())
                .userEntity(user)
                .build();
    }

    public Student toDomain(StudentEntity entity) {
        return Student.builder()
                .id(entity.getId())
                .name(entity.getName())
                .number(entity.getNumber())
                .profileImageUrl(entity.getProfileImageUrl())
                .classRoom(entity.getClassRoom())
                .department(entity.getDepartment())
                .grade(entity.getGrade())
                .build();
    }
}
