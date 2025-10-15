package team.retum.jobis.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import lombok.NoArgsConstructor;
import team.retum.jobis.domain.review.spi.vo.ReviewVO;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class QueryReviewsResponse {

    private final List<ReviewResponse> reviews;

    @Getter
    @NoArgsConstructor(force = true)
    @AllArgsConstructor(access = PRIVATE)
    @Builder
    public static class ReviewResponse {

        private Long reviewId;

        private String companyName;

        private String companyLogoUrl;

        private String writer;

        private int year;

        private String major;

        public static ReviewResponse from(ReviewVO vo) {
            return ReviewResponse.builder()
                .reviewId(vo.getReviewId())
                .companyName(vo.getCompanyName())
                .companyLogoUrl(vo.getCompanyLogoUrl())
                .writer(vo.getWriter())
                .year(vo.getYear())
                .major(vo.getMajor())
                .build();
        }
    }
}
