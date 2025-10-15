package team.retum.jobis.domain.review.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Question {

    private final Long id;

    private final String question;
}
