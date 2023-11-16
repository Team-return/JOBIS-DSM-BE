package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.service.GetRecruitmentDetailService;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryMyRecruitmentUseCase {

    private final SecurityPort securityPort;
    private final GetRecruitmentDetailService getRecruitmentDetailService;

    public QueryRecruitmentDetailResponse execute() {
        Long currentUserId = securityPort.getCurrentUserId();

        return getRecruitmentDetailService.execute(currentUserId);
    }
}
