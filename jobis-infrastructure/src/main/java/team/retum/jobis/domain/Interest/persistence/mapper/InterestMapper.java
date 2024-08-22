package team.retum.jobis.domain.interest.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;
import team.retum.jobis.domain.interest.persistence.entity.InterestEntity;
import team.retum.jobis.domain.interest.model.Interest;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;

@RequiredArgsConstructor
@Component
public class InterestMapper {

    private final StudentJpaRepository studentJpaRepository;
    private final CodeJpaRepository codeJpaRepository;

    public InterestEntity toEntity(Interest domain) {
        StudentEntity student = studentJpaRepository.getReferenceById(domain.getStudentId());
        CodeEntity code = codeJpaRepository.getReferenceById(domain.getCode());

        return InterestEntity.builder()
            .id(domain.getId())
            .studentEntity(student)
            .codeEntity(code)
            .build();
    }

    public Interest toDomain(InterestEntity entity) {
        return Interest.builder()
            .id(entity.getId())
            .studentId(entity.getStudent().getId())
            .code(entity.getCode().getCode())
            .build();
    }
}
