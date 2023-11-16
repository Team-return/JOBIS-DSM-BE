package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.code.service.GetKeywordsService;
import team.retum.jobis.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.service.GetRecruitmentDetailService;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryRecruitmentDetailUseCase {

    private final QueryRecruitmentPort queryRecruitmentPort;
    private final GetKeywordsService getKeywordsService;
    private final GetRecruitmentDetailService getRecruitmentDetailService;

    public QueryRecruitmentDetailResponse execute(Long recruitId) {

        return getRecruitmentDetailService.execute(recruitId);
    }
}
