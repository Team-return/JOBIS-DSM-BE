package team.retum.jobis.domain.review.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewVO {

    private final Long reviewId;

    private final String companyName;

    private final String writer;

    private final int year;

    private final String major;
}
