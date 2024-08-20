package team.retum.jobis.domain.interest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.interest.model.Interest;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class InterestResponse {

    private final Long id;
    private final Long studentId;
    private final Long code;
    private final String keyword;

    public static InterestResponse from(Interest interest, Code code) {
        return InterestResponse.builder()
            .id(interest.getId())
            .studentId(interest.getStudentId())
            .code(code.getId())
            .keyword(code.getKeyword())
            .build();
    }
}
