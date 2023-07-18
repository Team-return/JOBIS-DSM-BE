package team.retum.jobis.domain.review.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.code.persistence.Code;
import team.retum.jobis.domain.review.persistence.QnAElement;

import java.util.List;

@Getter
@Builder
public class QueryReviewDetailResponse {

    private final int year;
    private final String writer;
    private final List<QnAResponse> qnaResponses;

    @Getter
    @Builder
    public static class QnAResponse {
        private final String question;
        private final String answer;
        private final String area;

        public static List<QnAResponse> of(
                List<QnAElement> qnAElements,
                List<Code> codes
        ) {
            return qnAElements.stream()
                    .map(qnAElement -> {
                        String keyword = codes.stream()
                                .filter(code -> code.getId().equals(qnAElement.getCodeId()))
                                .map(Code::getKeyword)
                                .findFirst()
                                .orElse("");

                        return QnAResponse.builder()
                                .question(qnAElement.getQuestion())
                                .answer(qnAElement.getAnswer())
                                .area(keyword)
                                .build();
                    })
                    .toList();
        }
    }
}
