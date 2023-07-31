package team.retum.jobis.domain.review.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QnAElement {

    private final String question;

    private final String answer;

    private final Long codeId;
}
