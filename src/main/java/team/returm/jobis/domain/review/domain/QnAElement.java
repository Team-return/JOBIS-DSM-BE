package team.returm.jobis.domain.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.code.domain.Code;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QnAElement {

    private String question;

    private String answer;

    private Code code;

}
