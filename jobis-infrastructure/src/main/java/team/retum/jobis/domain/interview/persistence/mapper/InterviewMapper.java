package team.retum.jobis.domain.interview.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.interview.model.Interview;
import team.retum.jobis.domain.interview.persistence.entity.DocumentNumberEntity;
import team.retum.jobis.domain.interview.persistence.entity.InterviewEntity;
import team.retum.jobis.domain.interview.persistence.repository.DocumentNumberJpaRepository;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;

@RequiredArgsConstructor
@Component
public class InterviewMapper {

    private final StudentJpaRepository studentJpaRepository;
    private final DocumentNumberJpaRepository documentNumberJpaRepository;

    public InterviewEntity toEntity(Interview domain) {
        StudentEntity studentEntity = studentJpaRepository.getReferenceById(domain.getStudentId());
        DocumentNumberEntity documentNumberEntity = documentNumberJpaRepository.getReferenceById(domain.getDocumentNumberId());

        return InterviewEntity.builder()
            .id(domain.getId())
            .interviewType(domain.getInterviewType())
            .startDate(domain.getStartDate())
            .endDate(domain.getEndDate())
            .interviewTime(domain.getInterviewTime())
            .companyName(domain.getCompanyName())
            .location(domain.getLocation())
            .student(studentEntity)
            .documentNumber(documentNumberEntity)
            .build();
    }

    public Interview toDomain(InterviewEntity entity) {
        return Interview.builder()
            .id(entity.getId())
            .interviewType(entity.getInterviewType())
            .startDate(entity.getStartDate())
            .endDate(entity.getEndDate())
            .interviewTime(entity.getInterviewTime())
            .companyName(entity.getCompanyName())
            .location(entity.getLocation())
            .studentId(entity.getStudent().getId())
            .documentNumberId(entity.getDocumentNumber().getId())
            .build();
    }
}
