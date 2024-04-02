package team.retum.jobis.domain.review.spi.vo;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ReviewVO {

    private final Long reviewId;
    private final String writer;
    private final int year;
    private final String date;

    public ReviewVO(Long reviewId, String writer, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.writer = writer;
        this.year = createdAt.getYear();
        this.date = createdAt.format(DateTimeFormatter.ofPattern("MM-dd"));
    }
}
