package team.retum.jobis.domain.code.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.code.persistence.Code;
import team.retum.jobis.domain.code.persistence.enums.JobType;

import java.util.List;

@Getter
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    public static class CodeResponse {

        private final Long code;
        private final String keyword;
        private final JobType jobType;
    }
}
