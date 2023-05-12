package team.returm.jobis.domain.review.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryReviewsResponse {

    private final List<ReviewElement> reviews;


    @Getter
    @Builder
    public static class ReviewElement {
        private final String reviewId;
        private final int year;
        private final String writer;
        private final LocalDate createdDate;
    }
}
