package team.retum.jobis.domain.review.persistence.entity;

import com.example.jobisapplication.domain.review.model.QnAElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QnAElementEntity {

    @NotBlank
    private String question;

    @NotBlank
    private String answer;

    @NotNull
    private Long codeId;

    public QnAElement toDomainRequest() {
        return QnAElement.builder()
                .codeId(this.codeId)
                .answer(this.answer)
                .question(this.question)
                .build();
    }

}
