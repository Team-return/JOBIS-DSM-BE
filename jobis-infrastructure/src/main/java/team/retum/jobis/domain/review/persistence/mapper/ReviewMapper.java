package team.retum.jobis.domain.review.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyJpaRepository;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.persistence.entity.ReviewEntity;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;

@RequiredArgsConstructor
@Component
public class ReviewMapper {

    private final CompanyJpaRepository companyJpaRepository;
    private final StudentJpaRepository studentJpaRepository;

    public ReviewEntity toEntity(Review domain) {
        CompanyEntity companyEntity = companyJpaRepository.findById(domain.getCompanyId())
            .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
        StudentEntity studentEntity = studentJpaRepository.findById(domain.getStudentId())
            .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
        return ReviewEntity.builder()
            .id(domain.getId())
            .companyEntity(companyEntity)
            .studentEntity(studentEntity)
            .build();
    }

    public Review toDomain(ReviewEntity entity) {
        return Review.builder()
            .id(entity.getId())
            .companyId(entity.getCompany().getId())
            .studentId(entity.getStudent().getId())
            .createdAt(entity.getCreatedAt())
            .build();
    }
}
