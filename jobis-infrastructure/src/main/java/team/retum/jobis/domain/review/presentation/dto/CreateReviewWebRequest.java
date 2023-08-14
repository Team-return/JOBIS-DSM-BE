package team.retum.jobis.domain.review.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.review.dto.CreateReviewRequest;
import team.retum.jobis.domain.review.dto.CreateReviewRequest.QnAElement;
import team.retum.jobis.global.annotation.ValidListElements;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class CreateReviewWebRequest {

    @NotNull
    private Long companyId;

    @ValidListElements
    private List<@Valid QnAWebElement> qnaElements;

    @NotNull
    private Long applicationId;

    public CreateReviewRequest toDomainRequest() {
        return CreateReviewRequest.builder()
                .companyId(this.companyId)
                .qnaElements(
                        this.qnaElements.stream()
                                .map(webRequest -> QnAElement.builder()
                                        .question(webRequest.getQuestion())
                                        .answer(webRequest.getAnswer())
                                        .codeId(webRequest.getCodeId())
                                        .build())
                                .toList()
                )
                .applicationId(this.applicationId)
                .build();
    }

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
