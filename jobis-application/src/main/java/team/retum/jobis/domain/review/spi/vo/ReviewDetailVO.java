package team.retum.jobis.domain.review.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.review.model.InterviewLocation;
import team.retum.jobis.domain.review.model.InterviewType;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReviewDetailVO {

    private final Long reviewId;

    private final String companyName;

    private final String writer;

    private final LocalDate time;

    private final String major;

    private final InterviewType type;

    private final InterviewLocation location;

    private final Integer interviewerCount;

    private final String question;

    private final String answer;
}
