package team.retum.jobis.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.review.model.InterviewLocation;
import team.retum.jobis.domain.review.model.InterviewType;
import team.retum.jobis.domain.review.spi.vo.ReviewDetailVO;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class QueryReviewDetailResponse {

    private Long reviewId;

    private String companyName;

    private String writer;

    private int year;

    private String major;

    private InterviewType type;

    private InterviewLocation location;

    private Integer interviewerCount;

    private List<QnAResponse> qnAs;

    private String question;

    private String answer;

    public static QueryReviewDetailResponse from(ReviewDetailVO review, List<QnAResponse> qnAs) {
        return QueryReviewDetailResponse.builder()
            .reviewId(review.getReviewId())
            .companyName(review.getCompanyName())
            .writer(review.getWriter())
            .year(review.getYear())
            .major(review.getMajor())
            .type(review.getType())
            .location(review.getLocation())
            .interviewerCount(review.getInterviewerCount())
            .qnAs(qnAs)
            .question(review.getQuestion())
            .answer(review.getAnswer())
            .build();
    }
}
