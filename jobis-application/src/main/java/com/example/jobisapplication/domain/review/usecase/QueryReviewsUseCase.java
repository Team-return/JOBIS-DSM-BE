package com.example.jobisapplication.domain.review.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.company.spi.QueryCompanyPort;
import com.example.jobisapplication.domain.review.model.Review;
import com.example.jobisapplication.domain.review.spi.QueryReviewPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.company.exception.CompanyNotFoundException;
import com.example.jobisapplication.domain.review.dto.QueryReviewsResponse;
import com.example.jobisapplication.domain.review.dto.QueryReviewsResponse.ReviewResponse;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class QueryReviewsUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final QueryReviewPort queryReviewPort;

    public QueryReviewsResponse execute(Long companyId) {
        if (!queryCompanyPort.existsCompanyById(companyId)) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        List<Review> reviewEntities = queryReviewPort.queryAllReviewsByCompanyId(companyId);

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
