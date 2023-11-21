package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.domain.code.service.GetKeywordsService;
import team.retum.jobis.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.dto.response.RecruitAreaResponse;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetRecruitmentDetailService {

    private final QueryRecruitmentPort queryRecruitmentPort;

    public QueryRecruitmentDetailResponse execute(Recruitment recruitment) {
        RecruitmentDetailVO recruitmentDetail = queryRecruitmentPort.queryRecruitmentDetailById(recruitment.getId());
        List<RecruitAreaResponse> recruitAreaResponses = queryRecruitmentPort.queryRecruitAreasByRecruitmentId(recruitment.getId())
                .stream().map(recruitAreaResponse -> RecruitAreaResponse.builder()
                        .id(recruitAreaResponse.getId())
                        .job(recruitAreaResponse.getJob())
                        .tech(recruitAreaResponse.getTech())
                        .hiring(recruitAreaResponse.getHiring())
                        .majorTask(recruitAreaResponse.getMajorTask())
                        .preferentialTreatment(recruitAreaResponse.getPreferentialTreatment())
                        .build()
                ).toList();

        return QueryRecruitmentDetailResponse.of(recruitmentDetail, recruitAreaResponses);
    }
}
