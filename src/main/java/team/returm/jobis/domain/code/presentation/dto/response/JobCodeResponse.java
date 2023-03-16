package team.returm.jobis.domain.code.presentation.dto.response;

import team.returm.jobis.domain.code.domain.enums.JobType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobCodeResponse {
    private final Long code;
    private final String keyword;
    private final JobType jobType;
}
