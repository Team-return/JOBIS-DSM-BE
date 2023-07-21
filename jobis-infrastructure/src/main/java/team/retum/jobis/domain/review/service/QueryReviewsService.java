package team.retum.jobis.domain.review.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.persistence.CompanyPersistenceAdapter;
import com.example.jobisapplication.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.review.persistence.entity.ReviewEntity;
import team.retum.jobis.domain.review.persistence.repository.ReviewRepository;
import com.example.jobisapplication.domain.review.dto.QueryReviewsResponse;
import com.example.jobisapplication.domain.review.dto.QueryReviewsResponse.ReviewResponse;
import com.example.jobisapplication.common.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryReviewsService {

    private final CompanyPersistenceAdapter companyPersistenceAdapter;
    private final ReviewRepository reviewRepository;

    public QueryReviewsResponse execute(Long companyId) {
        if (!companyPersistenceAdapter.existsCompanyById(companyId)) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        List<ReviewEntity> reviewEntities = reviewRepository.findAllByCompanyId(companyId);

        return new QueryReviewsResponse(
                reviewEntities.stream()
                        .map(review -> ReviewResponse.builder()
                                .reviewId(review.getId())
                                .year(review.getYear())
                                .writer(review.getStudentName())
                                .build())
                        .toList()
        );
    }
}
