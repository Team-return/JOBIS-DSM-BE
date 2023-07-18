package team.retum.jobis.domain.review.persistence.entity;

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

}