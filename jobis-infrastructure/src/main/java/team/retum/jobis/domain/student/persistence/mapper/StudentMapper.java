package team.retum.jobis.domain.student.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.student.model.SchoolNumber;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import team.retum.jobis.domain.user.persistence.repository.UserJpaRepository;

@RequiredArgsConstructor
@Component
public class StudentMapper {

    private final UserJpaRepository userJpaRepository;

    public StudentEntity toEntity(Student domain) {
        UserEntity user = userJpaRepository.getReferenceById(domain.getId());

        return StudentEntity.builder()
            .id(domain.getId())
            .name(domain.getName())
            .grade(domain.getSchoolNumber().getGrade())
            .classRoom(domain.getSchoolNumber().getClassRoom())
            .number(domain.getSchoolNumber().getNumber())
            .gender(domain.getGender())
            .department(domain.getDepartment())
            .profileImageUrl(domain.getProfileImageUrl())
            .entranceYear(domain.getEntranceYear())
            .userEntity(user)
            .build();
    }

    public Student toDomain(StudentEntity entity) {
        return Student.builder()
            .id(entity.getId())
            .name(entity.getName())
            .schoolNumber(
                SchoolNumber.builder()
                    .grade(entity.getGrade())
                    .classRoom(entity.getClassRoom())
                    .number(entity.getNumber())
                    .build()
            )
            .gender(entity.getGender())
            .profileImageUrl(entity.getProfileImageUrl())
            .department(entity.getDepartment())
            .entranceYear(entity.getEntranceYear())
            .build();
    }
}
