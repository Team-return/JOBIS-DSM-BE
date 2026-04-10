package team.retum.jobis.domain.interview.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.interview.dto.InterviewFilter;
import team.retum.jobis.domain.interview.dto.response.QueryInterviewsResponse;
import team.retum.jobis.domain.interview.spi.QueryInterviewPort;
import team.retum.jobis.domain.recruitment.model.ProgressType;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryInterviewsUseCase {

    private final QueryInterviewPort queryInterviewPort;
    private final SecurityPort securityPort;

    public QueryInterviewsResponse execute(Integer year, Integer month, String companyName, ProgressType interviewType) {
        return new QueryInterviewsResponse(
            queryInterviewPort.getInterviewsBy(
                InterviewFilter.builder()
                    .year(year)
                    .month(month)
                    .companyName(companyName)
                    .interviewType(interviewType)
                    .studentId(resolveStudentId())
                    .build()
            )
        );
    }

    private Long resolveStudentId() {
        return securityPort.getCurrentUserAuthority() == Authority.STUDENT
            ? securityPort.getCurrentUserId()
            : null;
    }
}
