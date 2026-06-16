package team.retum.jobis.domain.review.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReviewVO {

    private final Long reviewId;

    private final String companyName;

    private final String companyLogoUrl;

    private final String writer;

    private final LocalDate time;

    private final String major;
}
