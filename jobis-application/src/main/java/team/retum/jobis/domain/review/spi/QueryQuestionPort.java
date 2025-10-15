package team.retum.jobis.domain.review.spi;

import team.retum.jobis.domain.review.model.Question;

import java.util.List;

public interface QueryQuestionPort {

    List<Question> queryAllQuestions();
}
