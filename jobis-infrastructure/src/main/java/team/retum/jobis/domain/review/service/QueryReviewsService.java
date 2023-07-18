package team.retum.jobis.domain.review.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.domain.repository.CompanyRepository;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.review.domain.Review;
import team.retum.jobis.domain.review.domain.repository.ReviewRepository;
import team.retum.jobis.domain.review.presentation.dto.QueryReviewsResponse;
import team.retum.jobis.domain.review.presentation.dto.QueryReviewsResponse.ReviewResponse;
import team.retum.jobis.global.annotation.Service;

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

        List<Review> reviews = reviewRepository.findAllByCompanyId(companyId);

        return new QueryReviewsResponse(
                reviews.stream()
                        .map(review -> ReviewResponse.builder()
                                .reviewId(review.getId())
                                .year(review.getYear())
                                .writer(review.getStudentName())
                                .build())
                        .toList()
        );
    }
}
