package team.retum.jobis.domain.review.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QnA {

    private final Long id;

    private final String question;

    private final String answer;

    private final Long reviewId;

    private final Long codeId;

}
