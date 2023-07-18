package team.retum.jobis.domain.review.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.review.presentation.dto.CreateReviewRequest;
import team.retum.jobis.domain.review.presentation.dto.QueryReviewDetailResponse;
import team.retum.jobis.domain.review.presentation.dto.QueryReviewsResponse;
import team.retum.jobis.domain.review.service.CreateReviewService;
import team.retum.jobis.domain.review.service.DeleteReviewService;
import team.retum.jobis.domain.review.service.QueryReviewDetailService;
import team.retum.jobis.domain.review.service.QueryReviewsService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewController {

    private final QueryReviewsService queryReviewsService;
    private final QueryReviewDetailService queryReviewDetailService;
    private final CreateReviewService createReviewService;
    private final DeleteReviewService deleteReviewService;


    @GetMapping("/{company-id}")
    public QueryReviewsResponse getReviews(
            @PathVariable(name = "company-id") Long companyId
    ) {
        return queryReviewsService.execute(companyId);
    }

    @GetMapping("/details/{review-id}")
    public QueryReviewDetailResponse getReviewDetails(
            @PathVariable(name = "review-id") String reviewId
    ) {
        return queryReviewDetailService.execute(reviewId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createReview(
            @RequestBody @Valid CreateReviewRequest request
    ) {
        createReviewService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{review-id}")
    public void deleteReview(
            @PathVariable(name = "review-id") String reviewId
    ) {
        deleteReviewService.execute(reviewId);
    }
}


