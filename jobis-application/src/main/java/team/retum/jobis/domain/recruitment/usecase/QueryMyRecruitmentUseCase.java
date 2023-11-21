package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.service.GetRecruitmentDetailService;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryMyRecruitmentUseCase {

    private final SecurityPort securityPort;
    private final GetRecruitmentDetailService getRecruitmentDetailService;
    private final QueryRecruitmentPort queryRecruitmentPort;

    public QueryRecruitmentDetailResponse execute() {
        Long currentUserId = securityPort.getCurrentUserId();
        Recruitment recruitment = queryRecruitmentPort.queryRecentRecruitmentByCompanyId(currentUserId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        return getRecruitmentDetailService.execute(recruitment);
    }
}
