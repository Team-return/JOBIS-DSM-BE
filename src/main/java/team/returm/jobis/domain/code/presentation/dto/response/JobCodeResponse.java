package team.returm.jobis.domain.code.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.code.domain.enums.JobType;

@Getter
@Builder
public class JobCodeResponse {
    private final Long code;
    private final String keyword;
    private final JobType jobType;
}
