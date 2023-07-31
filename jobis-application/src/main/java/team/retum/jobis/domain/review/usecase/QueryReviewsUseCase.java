package team.retum.jobis.domain.review.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.spi.QueryReviewPort;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.review.dto.QueryReviewsResponse;
import team.retum.jobis.domain.review.dto.QueryReviewsResponse.ReviewResponse;

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
