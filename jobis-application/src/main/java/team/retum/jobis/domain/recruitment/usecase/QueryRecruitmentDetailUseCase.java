package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.service.GetRecruitmentDetailService;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryRecruitmentDetailUseCase {

    private final GetRecruitmentDetailService getRecruitmentDetailService;
    private final QueryRecruitmentPort queryRecruitmentPort;

    public QueryRecruitmentDetailResponse execute(Long recruitId) {
        Recruitment recruitment = queryRecruitmentPort.queryRecruitmentById(recruitId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        return getRecruitmentDetailService.execute(recruitment);
    }
}
