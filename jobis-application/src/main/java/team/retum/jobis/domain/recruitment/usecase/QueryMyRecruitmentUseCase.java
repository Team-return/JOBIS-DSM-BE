package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.recruitment.dto.response.QueryMyRecruitmentResponse;
import team.retum.jobis.domain.recruitment.dto.response.RecruitAreaResponse;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryMyRecruitmentUseCase {

    private final SecurityPort securityPort;
    private final QueryRecruitmentPort queryRecruitmentPort;

    public QueryMyRecruitmentResponse execute() {
        Long currentUserId = securityPort.getCurrentUserId();
        Recruitment recruitment = queryRecruitmentPort.queryRecentRecruitmentByCompanyId(currentUserId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        RecruitmentDetailVO recruitmentDetail = queryRecruitmentPort.queryRecruitmentDetailById(recruitment.getId(), currentUserId);
        List<RecruitAreaResponse> recruitAreaResponses =
                queryRecruitmentPort.queryRecruitAreasByRecruitmentId(recruitment.getId()).stream()
                .map(recruitAreaResponse -> RecruitAreaResponse.builder()
                        .id(recruitAreaResponse.getId())
                        .job(recruitAreaResponse.getJob())
                        .tech(recruitAreaResponse.getTech())
                        .hiring(recruitAreaResponse.getHiring())
                        .majorTask(recruitAreaResponse.getMajorTask())
                        .preferentialTreatment(recruitAreaResponse.getPreferentialTreatment())
                        .build()
                ).toList();

        return QueryMyRecruitmentResponse.of(recruitmentDetail, recruitAreaResponses);
    }
}
