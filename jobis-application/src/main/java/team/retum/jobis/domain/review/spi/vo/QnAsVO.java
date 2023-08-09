package team.retum.jobis.domain.review.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QnAsVO {
    private final String question;
    private final String answer;
    private final String area;
}
