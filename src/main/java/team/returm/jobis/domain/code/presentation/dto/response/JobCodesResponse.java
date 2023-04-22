package team.returm.jobis.domain.code.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.code.domain.enums.JobType;

import java.util.List;

@Getter
@AllArgsConstructor
public class JobCodesResponse {

    private final List<JobCodeResponse> codes;

    @Getter
    @Builder
    public static class JobCodeResponse {

        private final Long code;
        private final String keyword;
        private final JobType jobType;
    }
}
