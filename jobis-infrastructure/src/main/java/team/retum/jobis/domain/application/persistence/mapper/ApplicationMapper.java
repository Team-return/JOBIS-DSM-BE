package team.retum.jobis.domain.application.persistence.mapper;

import com.example.jobisapplication.domain.application.model.Application;
import com.example.jobisapplication.domain.recruitment.exception.RecruitmentNotFoundException;
import com.example.jobisapplication.domain.student.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.application.persistence.entity.ApplicationEntity;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentJpaRepository;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;

@RequiredArgsConstructor
@Component
public class ApplicationMapper {

    private final StudentJpaRepository studentJpaRepository;
    private final RecruitmentJpaRepository recruitmentJpaRepository;

    public ApplicationEntity toEntity(Application domain) {
        StudentEntity student = studentJpaRepository.findById(domain.getStudentId())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        RecruitmentEntity recruitment = recruitmentJpaRepository.findById(domain.getRecruitmentId())
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        return ApplicationEntity.builder()
                .applicationStatus(domain.getApplicationStatus())
                .studentEntity(student)
                .recruitmentEntity(recruitment)
                .rejectionReason(domain.getRejectionReason())
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .build();
    }

    public Application toDomain(ApplicationEntity entity) {
        return Application.builder()
                .id(entity.getId())
                .applicationStatus(entity.getApplicationStatus())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .rejectionReason(entity.getRejectionReason())
                .studentId(entity.getStudentEntity().getId())
                .recruitmentId(entity.getRecruitmentEntity().getId())
                .build();
    }
}
