package team.retum.jobis.domain.review.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.review.persistence.QnAElementEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateReviewRequest {

    @NotNull
    private Long companyId;

    @Valid
    @NotNull
    private List<QnAElementEntity> qnaElementEntities;

    @NotNull
    private Long applicationId;

}
