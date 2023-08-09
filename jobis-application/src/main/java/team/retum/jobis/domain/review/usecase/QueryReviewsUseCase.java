package team.retum.jobis.domain.review.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.review.dto.QueryReviewsResponse;
import team.retum.jobis.domain.review.spi.QueryReviewPort;

@RequiredArgsConstructor
@UseCase
public class QueryReviewsUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final QueryReviewPort queryReviewPort;

    public QueryReviewsResponse execute(Long companyId) {
        if (!queryCompanyPort.existsCompanyById(companyId)) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        return new QueryReviewsResponse(queryReviewPort.queryAllReviewsByCompanyId(companyId));
    }
}
