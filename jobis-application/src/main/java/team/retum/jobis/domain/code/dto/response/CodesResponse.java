package team.retum.jobis.domain.code.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.model.JobType;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CodesResponse {

    private final List<CodeResponse> codes;

    public static CodeResponse of(Code code) {
        return CodeResponse.builder()
            .code(code.getId())
            .keyword(code.getKeyword())
            .jobType(code.getJobType())
            .build();
    }

    @Getter
    @NoArgsConstructor(force = true)
    @AllArgsConstructor
    @Builder
    public static class CodeResponse {

        private final Long code;
        private final String keyword;
        private final JobType jobType;
    }
}
