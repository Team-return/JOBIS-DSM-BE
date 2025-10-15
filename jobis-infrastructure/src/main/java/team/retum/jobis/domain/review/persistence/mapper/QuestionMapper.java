package team.retum.jobis.domain.review.persistence.mapper;

import org.springframework.stereotype.Component;
import team.retum.jobis.domain.review.model.Question;
import team.retum.jobis.domain.review.persistence.entity.QuestionEntity;

@Component
public class QuestionMapper {

    public Question toDomain(QuestionEntity entity) {
        return Question.builder()
            .id(entity.getId())
            .question(entity.getQuestion())
            .build();
    }

    public QuestionEntity toEntity(Question domain) {
        return QuestionEntity.builder()
            .id(domain.getId())
            .question(domain.getQuestion())
            .build();
    }
}
