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
import team.retum.jobis.domain.review.presentation.dto.CreateReviewWebRequest;
import com.example.jobisapplication.domain.review.dto.QueryReviewDetailResponse;
import com.example.jobisapplication.domain.review.dto.QueryReviewsResponse;
import com.example.jobisapplication.domain.review.usecase.CreateReviewUseCase;
import com.example.jobisapplication.domain.review.usecase.DeleteReviewUseCase;
import com.example.jobisapplication.domain.review.usecase.QueryReviewDetailUseCase;
import com.example.jobisapplication.domain.review.usecase.QueryReviewsUseCase;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewPersistenceAdapter {

    private final QueryReviewsUseCase queryReviewsUseCase;
    private final QueryReviewDetailUseCase queryReviewDetailUseCase;
    private final CreateReviewUseCase createReviewUseCase;
    private final DeleteReviewUseCase deleteReviewUseCase;


    @GetMapping("/{company-id}")
    public QueryReviewsResponse getReviews(
            @PathVariable(name = "company-id") Long companyId
    ) {
        return queryReviewsUseCase.execute(companyId);
    }

    @GetMapping("/details/{review-id}")
    public QueryReviewDetailResponse getReviewDetails(
            @PathVariable(name = "review-id") String reviewId
    ) {
        return queryReviewDetailUseCase.execute(reviewId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createReview(
            @RequestBody @Valid CreateReviewWebRequest webRequest
    ) {
        createReviewUseCase.execute(webRequest.toDomainRequest());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{review-id}")
    public void deleteReview(
            @PathVariable(name = "review-id") String reviewId
    ) {
        deleteReviewUseCase.execute(reviewId);
    }
}


