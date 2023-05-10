package team.returm.jobis.domain.review.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.returm.jobis.domain.review.presentation.dto.CreateReviewRequest;
import team.returm.jobis.domain.review.service.CreateReviewService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewController {

    private final CreateReviewService createReviewService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createReview(@RequestBody @Valid CreateReviewRequest request) {
        createReviewService.execute(request);
    }
}
