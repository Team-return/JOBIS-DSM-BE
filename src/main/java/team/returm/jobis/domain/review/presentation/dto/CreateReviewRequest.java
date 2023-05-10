package team.returm.jobis.domain.review.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.review.domain.QnAElement;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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

    @NotBlank
    private String companyName;

    @NotBlank
    private String studentName;

    @NotNull
    private int year;

}
