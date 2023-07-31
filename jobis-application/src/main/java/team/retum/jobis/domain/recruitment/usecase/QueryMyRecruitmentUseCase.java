package team.retum.jobis.domain.recruitment.usecase;

import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
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
public class QueryMyRecruitmentUseCase {

    private final SecurityPort securityPort;
    private final QueryRecruitmentPort queryRecruitmentPort;

    public QueryRecruitmentDetailResponse execute() {
        Long currentUserId = securityPort.getCurrentUserId();

        Recruitment recruitment = queryRecruitmentPort.queryRecentRecruitmentByCompanyId(currentUserId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);


        RecruitmentDetailVO recruitmentDetail = queryRecruitmentPort.queryRecruitmentDetailById(recruitment.getId());
        List<RecruitAreaResponse> recruitAreaResponses =
                queryRecruitmentPort.queryRecruitAreasByRecruitmentId(recruitment.getId());
        return QueryRecruitmentDetailResponse.of(recruitmentDetail, recruitAreaResponses);
    }
}
