package team.retum.jobis.domain.review.presentation;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.domain.review.dto.response.QueryReviewDetailResponse;
import team.retum.jobis.domain.review.dto.response.QueryReviewsResponse;
import team.retum.jobis.domain.review.model.InterviewLocation;
import team.retum.jobis.domain.review.model.InterviewType;
import team.retum.jobis.domain.review.dto.response.QueryReviewOptionsResponse;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import team.retum.jobis.domain.review.presentation.dto.request.CreateReviewWebRequest;
import team.retum.jobis.domain.review.usecase.CreateReviewUseCase;
import team.retum.jobis.domain.review.usecase.DeleteReviewUseCase;
import team.retum.jobis.domain.review.usecase.QueryReviewOptionsUseCase;
import team.retum.jobis.domain.review.usecase.QueryReviewDetailUseCase;
import team.retum.jobis.domain.review.usecase.QueryReviewsUseCase;

import static team.retum.jobis.global.config.cache.CacheName.REVIEW;

@CacheConfig(cacheNames = REVIEW)
@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewWebAdapter {

    private final CreateReviewUseCase createReviewUseCase;
    private final QueryReviewsUseCase queryReviewsUseCase;
    private final QueryReviewDetailUseCase queryReviewDetailUseCase;
    private final QueryReviewOptionsUseCase queryReviewOptionsUseCase;
    private final DeleteReviewUseCase deleteReviewUseCase;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createReview(
        @RequestBody @Valid CreateReviewWebRequest request
    ) {
        createReviewUseCase.execute(request.toRequest());
    }

    @GetMapping("/options")
    public QueryReviewOptionsResponse queryReviewOptions() {
        return queryReviewOptionsUseCase.execute();
    }

    @Cacheable(condition = "#page <= 5")
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

    @Cacheable
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

    @GetMapping("/{review-id}")
    public QueryReviewDetailResponse getReviewDetail(@PathVariable(name = "review-id") Long reviewId) {
        return queryReviewDetailUseCase.execute(reviewId);
    }

    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{review-id}")
    public void deleteReview(
        @PathVariable(name = "review-id") Long reviewId
    ) {
        deleteReviewUseCase.execute(reviewId);
    }
}
