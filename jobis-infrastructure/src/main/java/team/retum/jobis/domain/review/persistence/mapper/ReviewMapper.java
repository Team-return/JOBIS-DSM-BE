package team.retum.jobis.domain.review.persistence.mapper;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyJpaRepository;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.persistence.entity.ReviewEntity;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;

@RequiredArgsConstructor
@Component
public class ReviewMapper {

    private final CompanyJpaRepository companyJpaRepository;
    private final StudentJpaRepository studentJpaRepository;
    private final CodeJpaRepository codeJpaRepository;

    public ReviewEntity toEntity(Review domain) {
        CompanyEntity companyEntity = companyJpaRepository.getReferenceById(domain.getCompanyId());
        StudentEntity studentEntity = studentJpaRepository.getReferenceById(domain.getStudentId());
        CodeEntity codeEntity = codeJpaRepository.getReferenceById(domain.getCodeId());

        return ReviewEntity.builder()
            .id(domain.getId())
            .company(companyEntity)
            .student(studentEntity)
            .interviewType(domain.getInterviewType())
            .interviewLocation(domain.getInterviewLocation())
            .code(codeEntity)
            .interviewerCount(domain.getInterviewerCount())
            .answer(domain.getAnswer())
            .question(domain.getQuestion())
            .build();
    }

    public Review toDomain(ReviewEntity entity) {

        return Review.builder()
            .id(entity.getId())
            .companyId(entity.getCompany().getId())
            .studentId(entity.getStudent().getId())
            .interviewType(entity.getInterviewType())
            .interviewLocation(entity.getInterviewLocation())
            .codeId(entity.getCode().getCode())
            .interviewerCount(entity.getInterviewerCount())
            .qnAS(Collections.emptyList())
            .createdAt(entity.getCreatedAt())
            .answer(entity.getAnswer())
            .question(entity.getQuestion())
            .build();
    }
}
