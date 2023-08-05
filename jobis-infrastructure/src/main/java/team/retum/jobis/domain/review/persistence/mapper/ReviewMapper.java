package team.retum.jobis.domain.review.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.persistence.entity.ReviewEntity;

@RequiredArgsConstructor
@Component
public class ReviewMapper {

    public ReviewEntity toEntity(Review domain) {
        return ReviewEntity.builder()
                .id(domain.getId())
                .year(domain.getYear())
                .companyId(domain.getCompanyId())
                .studentName(domain.getStudentName())
                .build();
    }

    public Review toDomain(ReviewEntity entity) {
        return Review.builder()
                .id(entity.getId())
                .year(entity.getYear())
                .companyId(entity.getCompanyId())
                .studentName(entity.getStudentName())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}
