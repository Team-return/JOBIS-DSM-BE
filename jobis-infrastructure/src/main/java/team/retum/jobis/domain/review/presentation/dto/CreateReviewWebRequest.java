package team.retum.jobis.domain.review.presentation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.global.annotation.ValidListElements;

import java.util.List;

@Getter
public class CreateReviewWebRequest {

    @NotNull
    private Long companyId;

    @ValidListElements
    private List<@Valid QnAWebElement> qnaElements;

    @Getter
    @Builder
    public static class QnAWebElement {

        @Size(max = 50)
        @NotBlank
        private String question;

        @Size(max = 500)
        @NotBlank
        private String answer;

        @NotNull
        private Long codeId;
    }
}
