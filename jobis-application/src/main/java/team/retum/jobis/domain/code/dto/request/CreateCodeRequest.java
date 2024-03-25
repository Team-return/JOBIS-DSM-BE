package team.retum.jobis.domain.code.dto.request;

import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.model.JobType;

public record CreateCodeRequest(
    CodeType codeType,
    JobType jobType,
    String keyword
) {

}
