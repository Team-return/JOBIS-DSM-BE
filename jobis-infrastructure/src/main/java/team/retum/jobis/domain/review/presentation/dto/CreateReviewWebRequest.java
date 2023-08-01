package team.retum.jobis.domain.review.presentation.dto;

import team.retum.jobis.domain.review.dto.CreateReviewRequest;
import lombok.Getter;
import team.retum.jobis.domain.review.persistence.entity.QnAElementEntity;
import team.retum.jobis.global.annotation.ValidListElements;

import java.util.List;

@Getter
public class CreateReviewWebRequest {

    private Long companyId;

    @ValidListElements
    private List<QnAElementEntity> qnaElementEntities;

    private Long applicationId;

    public CreateReviewRequest toDomainRequest() {
        return CreateReviewRequest.builder()
                .companyId(this.companyId)
                .qnaElementEntities(
                        this.qnaElementEntities.stream()
                                .map(QnAElementEntity::toDomainRequest).toList()
                )
                .applicationId(this.applicationId)
                .build();
    }
}
