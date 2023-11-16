package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.service.GetRecruitmentDetailService;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryRecruitmentDetailUseCase {

    private final GetRecruitmentDetailService getRecruitmentDetailService;

    public QueryRecruitmentDetailResponse execute(Long recruitId) {

        return getRecruitmentDetailService.execute(recruitId);
    }
}
