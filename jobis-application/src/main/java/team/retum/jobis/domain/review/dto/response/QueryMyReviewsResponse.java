package team.retum.jobis.domain.review.dto.response;

import lombok.Builder;
import team.retum.jobis.domain.review.spi.vo.MyReviewVO;

import java.util.List;

public record QueryMyReviewsResponse(
    List<MyReviewResponse> reviews
) {
    @Builder
    public record MyReviewResponse(
        Long reviewId,
        String companyName
    ) {
        public static MyReviewResponse from(MyReviewVO vo) {
            return MyReviewResponse.builder()
                .reviewId(vo.getReviewId())
                .companyName(vo.getCompanyName())
                .build();
        }
    }
}
