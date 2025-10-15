package team.retum.jobis.domain.review.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QnAVO {

    private final Long id;

    private final String question;

    private final String answer;
}
