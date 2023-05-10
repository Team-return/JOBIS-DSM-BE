package team.returm.jobis.domain.review.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.returm.jobis.domain.review.presentation.dto.QueryReviewsResponse;
import team.returm.jobis.domain.review.service.QueryReviewsService;

@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewController {

    private final QueryReviewsService queryReviewsService;

    @GetMapping("/{company-id}")
    public QueryReviewsResponse getReviews(
            @PathVariable(name = "company-id") Long companyId
    ) {
        return queryReviewsService.execute(companyId);
    }
}
