package team.retum.jobis.domain.review.presentation.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.review.dto.request.CreateReviewRequest;
import team.retum.jobis.domain.review.model.InterviewLocation;
import team.retum.jobis.domain.review.model.InterviewType;
import team.retum.jobis.global.annotation.ValidListElements;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateReviewWebRequest {

    @NotNull
    private InterviewType interviewType;

    @NotNull
    private InterviewLocation location;

    @NotNull
    private Long companyId;

    @NotNull
    private Long jobCode;

    @NotNull
    private Integer interviewerCount;

    @ValidListElements
    private List<QnARequest> qnas;

    private String question;

    private String answer;

    public CreateReviewRequest toRequest() {
        return CreateReviewRequest.builder()
            .interviewType(this.interviewType)
            .location(this.location)
            .companyId(this.companyId)
            .jobCode(this.jobCode)
            .interviewerCount(this.interviewerCount)
            .qnas(this.qnas.stream().map(QnARequest::toRequest).toList())
            .question(this.question)
            .answer(this.answer)
            .build();
    }

    @Getter
    @NoArgsConstructor
    public static class QnARequest {

        @NotNull
        private Long questionId;

        @NotBlank
        private String answer;

        public CreateReviewRequest.CreateQnARequest toRequest() {
            return CreateReviewRequest.CreateQnARequest.builder()
                .questionId(this.questionId)
                .answer(this.answer)
                .build();
        }
    }
}
