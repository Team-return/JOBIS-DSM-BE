package team.retum.jobis.domain.review.spi.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewVO {

    private final Long reviewId;
    private final String companyName;
    private final String writer;
    private final int year;
    private final String major;

    public ReviewVO(Long reviewId, String companyName, String writer, LocalDateTime createdAt, String major) {
        this.reviewId = reviewId;
        this.companyName = companyName;
        this.writer = writer;
        this.year = createdAt.getYear();
        this.major = major;
    }
}
