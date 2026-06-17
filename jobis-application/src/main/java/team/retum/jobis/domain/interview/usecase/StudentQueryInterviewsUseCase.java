package team.retum.jobis.domain.interview.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.interview.dto.response.QueryInterviewsResponse;
import team.retum.jobis.domain.interview.spi.QueryInterviewPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class StudentQueryInterviewsUseCase {

    private final QueryInterviewPort queryInterviewPort;
    private final SecurityPort securityPort;

    public QueryInterviewsResponse execute() {
        return new QueryInterviewsResponse(
            queryInterviewPort.getInterviewsByStudentId(securityPort.getCurrentUserId())
        );
    }
}
