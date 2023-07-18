package team.retum.jobis.domain.review.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.review.domain.QnAElement;

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
    private List<QnAElement> qnaElements;

    @NotNull
    private Long applicationId;

}
