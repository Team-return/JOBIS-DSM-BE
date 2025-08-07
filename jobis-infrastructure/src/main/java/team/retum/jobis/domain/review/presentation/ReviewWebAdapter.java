package team.retum.jobis.domain.review.presentation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.domain.review.dto.QnAElement;
import team.retum.jobis.domain.review.dto.response.QueryReviewsResponse;
import team.retum.jobis.domain.review.model.InterviewLocation;
import team.retum.jobis.domain.review.model.InterviewType;
import team.retum.jobis.domain.review.presentation.dto.CreateReviewWebRequest;
import team.retum.jobis.domain.review.usecase.CreateReviewUseCase;
import team.retum.jobis.domain.review.usecase.DeleteReviewUseCase;
import team.retum.jobis.domain.review.usecase.QueryReviewsUseCase;

@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewWebAdapter {

    private final QueryReviewsUseCase queryReviewsUseCase;
    private final CreateReviewUseCase createReviewUseCase;
    private final DeleteReviewUseCase deleteReviewUseCase;

    @GetMapping
    public QueryReviewsResponse getReviews(
        @RequestParam(value = "page", required = false, defaultValue = "1") @Positive Integer page,
        @RequestParam(value = "type", required = false) InterviewType type,
        @RequestParam(value = "location", required = false) InterviewLocation location,
        @RequestParam(value = "company-id", required = false) Long companyId,
        @RequestParam(value = "year", required = false) Integer year,
        @RequestParam(value = "code", required = false) Long code
    ) {
        return queryReviewsUseCase.execute(page, type, location, companyId, year, code);
    }

    @GetMapping("/count")
    public TotalPageCountResponse getReviewsCount(
        @RequestParam(value = "type", required = false) InterviewType type,
        @RequestParam(value = "location", required = false) InterviewLocation location,
        @RequestParam(value = "company_id", required = false) Long companyId,
        @RequestParam(value = "year", required = false) Integer year,
        @RequestParam(value = "code", required = false) Long code
    ) {
        return queryReviewsUseCase.getTotalPageCount(type, location, companyId, year, code);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createReview(
        @RequestBody @Valid CreateReviewWebRequest webRequest
    ) {
        createReviewUseCase.execute(
            webRequest.getCompanyId(),
            webRequest.getQnaElements().stream()
                .map(qnAWebElement -> new QnAElement(
                    qnAWebElement.getQuestion(),
                    qnAWebElement.getAnswer(),
                    qnAWebElement.getCodeId()
                ))
                .toList()
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{review-id}")
    public void deleteReview(
        @PathVariable(name = "review-id") Long reviewId
    ) {
        deleteReviewUseCase.execute(reviewId);
    }
}
