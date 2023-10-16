package team.retum.jobis.domain.review.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyJpaRepository;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.persistence.entity.ReviewEntity;

@RequiredArgsConstructor
@Component
public class ReviewMapper {

    private final CompanyJpaRepository companyJpaRepository;

    public ReviewEntity toEntity(Review domain) {
        CompanyEntity companyEntity = companyJpaRepository.findById(domain.getCompanyId())
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
        return ReviewEntity.builder()
                .id(domain.getId())
                .companyEntity(companyEntity)
                .studentName(domain.getStudentName())
                .studentGender(domain.getStudentGender())
                .studentDepartment(domain.getStudentDepartment())
                .build();
    }

    public Review toDomain(ReviewEntity entity) {
        return Review.builder()
                .id(entity.getId())
                .companyId(entity.getCompany().getId())
                .studentName(entity.getStudentName())
                .studentGender(entity.getStudentGender())
                .studentDepartment(entity.getStudentDepartment())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
