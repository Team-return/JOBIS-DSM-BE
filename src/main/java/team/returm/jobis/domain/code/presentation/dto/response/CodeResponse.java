package team.returm.jobis.domain.code.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CodeResponse {
    private final Long code;
    private final String keyword;
}
