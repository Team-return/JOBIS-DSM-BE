package team.retum.jobis.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.review.spi.vo.QnAVO;

@Getter
@Builder
@AllArgsConstructor
public class QnAResponse {

    private Long id;

    private String question;

    private String answer;

    public static QnAResponse from(QnAVO vo) {
        return QnAResponse.builder()
            .id(vo.getId())
            .question(vo.getQuestion())
            .answer(vo.getAnswer())
            .build();
    }
}
