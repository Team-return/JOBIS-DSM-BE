package team.retum.jobis.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import lombok.NoArgsConstructor;
import team.retum.jobis.domain.review.spi.vo.ReviewVO;

import java.time.LocalDate;
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

        private LocalDate time;

        // year만 읽는 구버전 앱 호환용(#1176). 캐시 역직렬화를 위해 계산 getter가 아닌 필드로 둔다
        private Integer year;

        private String major;

        public static ReviewResponse from(ReviewVO vo) {
            return ReviewResponse.builder()
                .reviewId(vo.getReviewId())
                .companyName(vo.getCompanyName())
                .companyLogoUrl(vo.getCompanyLogoUrl())
                .writer(vo.getWriter())
                .time(vo.getTime())
                .year(vo.getTime() == null ? null : vo.getTime().getYear())
                .major(vo.getMajor())
                .build();
        }
    }
}
