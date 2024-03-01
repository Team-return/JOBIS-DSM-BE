package team.retum.jobis.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.review.spi.vo.QnAVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryReviewDetailResponse {

    private final List<QnAVO> qnaResponses;
}
