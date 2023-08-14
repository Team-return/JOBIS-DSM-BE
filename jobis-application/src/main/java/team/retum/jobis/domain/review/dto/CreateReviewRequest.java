package team.retum.jobis.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CreateReviewRequest {

    private Long companyId;

    private List<QnAElement> qnaElements;

    private Long applicationId;

    @Getter
    @Builder
    public static class QnAElement {
        private String question;
        private String answer;
        private Long codeId;
    }
}
