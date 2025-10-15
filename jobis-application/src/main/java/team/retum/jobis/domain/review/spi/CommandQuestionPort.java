package team.retum.jobis.domain.review.spi;

import team.retum.jobis.domain.review.model.Question;

public interface CommandQuestionPort {

    Question save(Question question);
}
