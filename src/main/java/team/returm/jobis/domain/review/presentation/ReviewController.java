package team.returm.jobis.domain.review.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.returm.jobis.domain.review.presentation.dto.CreateReviewRequest;
import team.returm.jobis.domain.review.service.CreateReviewService;
import team.returm.jobis.domain.review.service.DeleteReviewService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewController {

    private final CreateReviewService createReviewService;
    private final DeleteReviewService deleteReviewService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createReview(@RequestBody @Valid CreateReviewRequest request) {
        createReviewService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{review-id}")
    public void deleteReview(@PathVariable(name = "review-id") String reviewId) {
        deleteReviewService.execute(reviewId);
    }
}
