package team.retum.jobis.domain.review.persistence.mapper;

import team.retum.jobis.domain.review.model.QnAElement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.review.persistence.entity.QnAElementEntity;

@RequiredArgsConstructor
@Component
public class QnAElementMapper {

    public QnAElementEntity toEntity(QnAElement domain) {
        return new QnAElementEntity(domain.getQuestion(), domain.getAnswer(), domain.getCodeId());
    }

    public QnAElement toDomain(QnAElementEntity entity) {
        return QnAElement.builder()
                .codeId(entity.getCodeId())
                .answer(entity.getAnswer())
                .question(entity.getQuestion())
                .build();
    }
}
