package team.retum.jobis.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.review.spi.vo.ReviewVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryReviewsResponse {

    private final List<ReviewVO> reviews;

}
