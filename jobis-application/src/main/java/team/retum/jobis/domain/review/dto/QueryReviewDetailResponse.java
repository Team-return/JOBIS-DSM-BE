package team.retum.jobis.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.review.spi.vo.QnAsVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryReviewDetailResponse {

    private final List<QnAsVO> qnaResponses;
}
