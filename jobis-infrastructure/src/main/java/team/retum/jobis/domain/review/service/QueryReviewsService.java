package team.retum.jobis.domain.review.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.persistence.repository.CompanyRepository;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.review.persistence.ReviewEntity;
import team.retum.jobis.domain.review.persistence.repository.ReviewRepository;
import team.retum.jobis.domain.review.presentation.dto.QueryReviewsResponse;
import team.retum.jobis.domain.review.presentation.dto.QueryReviewsResponse.ReviewResponse;
import com.example.jobisapplication.common.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryReviewsService {

    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;

    public QueryReviewsResponse execute(Long companyId) {
        if (!companyRepository.existsCompanyById(companyId)) {
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
