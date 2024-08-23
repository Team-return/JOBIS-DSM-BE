package team.retum.jobis.domain.interest.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.interest.dto.response.InterestResponse;
import team.retum.jobis.domain.interest.spi.QueryInterestPort;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryMyInterestsUseCase {

    private final QueryInterestPort queryInterestPort;
    private final SecurityPort securityPort;

    public List<InterestResponse> execute() {
        Long studentId = securityPort.getCurrentStudent().getId();
        return queryInterestPort.getResponsesByStudentId(studentId);
    }
}
