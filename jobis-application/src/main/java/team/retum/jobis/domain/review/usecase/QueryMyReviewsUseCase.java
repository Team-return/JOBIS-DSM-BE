package team.retum.jobis.domain.review.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.review.dto.response.QueryMyReviewsResponse;
import team.retum.jobis.domain.review.spi.ReviewPort;
import team.retum.jobis.domain.student.model.Student;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryMyReviewsUseCase {

    private final SecurityPort securityPort;
    private final ReviewPort reviewPort;

    public QueryMyReviewsResponse execute() {
        Student student = securityPort.getCurrentStudent();

        List<QueryMyReviewsResponse.MyReviewResponse> reviews =
            reviewPort.getMyReviewsById(student.getId())
                .stream()
                .map(QueryMyReviewsResponse.MyReviewResponse::from)
                .toList();

        return new QueryMyReviewsResponse(reviews);
    }
}
