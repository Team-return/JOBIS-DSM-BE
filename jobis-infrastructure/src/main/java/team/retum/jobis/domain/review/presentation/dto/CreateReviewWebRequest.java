package team.retum.jobis.domain.review.presentation.dto;

import lombok.Getter;
import team.retum.jobis.domain.review.persistence.entity.QnAElementEntity;

import java.util.List;

@Getter
public class CreateReviewWebRequest {

    private Long companyId;

    private List<QnAElementEntity> qnaElementEntities;

    private Long applicationId;
}
