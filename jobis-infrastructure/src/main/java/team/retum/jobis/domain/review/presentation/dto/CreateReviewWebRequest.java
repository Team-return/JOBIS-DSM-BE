package team.retum.jobis.domain.review.presentation.dto;

import lombok.Getter;
import team.retum.jobis.domain.review.dto.CreateReviewRequest;
import team.retum.jobis.domain.review.dto.CreateReviewRequest.QnAElement;
import team.retum.jobis.global.annotation.ValidListElements;

import java.util.List;

@Getter
public class CreateReviewWebRequest {

    private Long companyId;

    @ValidListElements
    private List<QnAElement> qnaElements;

    private Long applicationId;

    public CreateReviewRequest toDomainRequest() {
        return CreateReviewRequest.builder()
                .companyId(this.companyId)
                .qnAs(this.qnaElements)
                .applicationId(this.applicationId)
                .build();
    }
}
