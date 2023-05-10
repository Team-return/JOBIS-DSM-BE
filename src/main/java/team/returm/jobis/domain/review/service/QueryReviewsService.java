package team.returm.jobis.domain.review.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.company.domain.repository.CompanyRepository;
import team.returm.jobis.domain.company.exception.CompanyNotFoundException;
import team.returm.jobis.domain.review.domain.Review;
import team.returm.jobis.domain.review.domain.repository.ReviewRepository;
import team.returm.jobis.domain.review.presentation.dto.QueryReviewsResponse;
import team.returm.jobis.global.annotation.Service;

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

        List<Review> reviews = reviewRepository.findByCompanyIdIn(companyId);

        return new QueryReviewsResponse(
                reviews.stream()
                        .map(review -> QueryReviewsResponse.ReviewElement.builder()
                                .reviewId(review.getId())
                                .year(review.getYear())
                                .writer(review.getStudentName())
                                .createdDate(review.getCreatedDate())
                                .build())
                        .toList()
        );
    }
}
