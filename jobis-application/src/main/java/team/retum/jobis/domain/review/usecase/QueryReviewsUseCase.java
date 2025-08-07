package team.retum.jobis.domain.review.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.common.util.NumberUtil;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.review.dto.ReviewFilter;
import team.retum.jobis.domain.review.dto.response.QueryReviewsResponse;
import team.retum.jobis.domain.review.model.InterviewLocation;
import team.retum.jobis.domain.review.model.InterviewType;
import team.retum.jobis.domain.review.spi.QueryReviewPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class QueryReviewsUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final QueryReviewPort queryReviewPort;
    private final QueryCodePort queryCodePort;

    public QueryReviewsResponse execute(
        Integer page,
        InterviewType type,
        InterviewLocation location,
        Long companyId,
        Integer year,
        Long code
    ) {
        ReviewFilter filter = ReviewFilter.builder()
            .page(page)
            .type(type)
            .location(location)
            .companyId(companyId)
            .year(year)
            .code(code)
            .build();

        if (companyId != null && !queryCompanyPort.existsById(companyId)) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        if (code != null && !queryCodePort.existsByCodeId(code)) {
            throw CodeNotFoundException.EXCEPTION;
        }

        List<QueryReviewsResponse.ReviewResponse> reviews =
            queryReviewPort.getAllByFilter(filter)
                .stream()
                .map(QueryReviewsResponse.ReviewResponse::from)
                .toList();

        return new QueryReviewsResponse(reviews);
    }

    public TotalPageCountResponse getTotalPageCount(
        InterviewType type,
        InterviewLocation location,
        Long companyId,
        Integer year,
        Long code
    ) {
        ReviewFilter filter = ReviewFilter.builder()
            .type(type)
            .location(location)
            .companyId(companyId)
            .year(year)
            .code(code)
            .build();

        int totalPageCount = NumberUtil.getTotalPageCount(
            queryReviewPort.getCountBy(filter), filter.getLimit()
        );

        return new TotalPageCountResponse(totalPageCount);
    }
}
