package team.retum.jobis.domain.review.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.review.model.QnAElement;

import java.util.List;

@Getter
@Builder
public class CreateReviewRequest {

    private Long companyId;

    private List<QnAElement> qnaElementEntities;

    private Long applicationId;
}
