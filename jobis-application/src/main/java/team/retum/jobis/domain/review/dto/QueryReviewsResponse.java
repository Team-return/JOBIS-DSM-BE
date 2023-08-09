package team.retum.jobis.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.review.spi.vo.ReviewsVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryReviewsResponse {

    private final List<ReviewsVO> reviews;

}
