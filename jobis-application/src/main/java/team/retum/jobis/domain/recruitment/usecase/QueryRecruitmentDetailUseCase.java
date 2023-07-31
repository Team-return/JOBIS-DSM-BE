package team.retum.jobis.domain.recruitment.usecase;

import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.dto.response.RecruitAreaResponse;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryRecruitmentDetailUseCase {

    private final QueryRecruitmentPort queryRecruitmentPort;

    public QueryRecruitmentDetailResponse execute(Long recruitId) {
        Recruitment recruitment = queryRecruitmentPort.queryRecruitmentById(recruitId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        RecruitmentDetailVO recruitmentDetail = queryRecruitmentPort.queryRecruitmentDetailById(recruitment.getId());
        List<RecruitAreaResponse> recruitAreaResponses =
                queryRecruitmentPort.queryRecruitAreasByRecruitmentId(recruitment.getId());
        return QueryRecruitmentDetailResponse.of(recruitmentDetail, recruitAreaResponses);
    }
}
