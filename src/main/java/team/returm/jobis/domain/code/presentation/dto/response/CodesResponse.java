package team.returm.jobis.domain.code.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CodesResponse {

    private final List<CodeResponse> codes;

    @Getter
    @Builder
    public static class CodeResponse {

        private final Long code;
        private final String keyword;
    }
}
