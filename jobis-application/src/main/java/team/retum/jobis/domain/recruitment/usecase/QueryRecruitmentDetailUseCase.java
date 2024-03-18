package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.dto.response.RecruitAreaResponse;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.JobResponse;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.model.TechResponse;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryRecruitmentDetailUseCase {

    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SecurityPort securityPort;

    public QueryRecruitmentDetailResponse execute(Long recruitId) {
        Recruitment recruitment = queryRecruitmentPort.queryRecruitmentById(recruitId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        RecruitmentDetailVO recruitmentDetail = queryRecruitmentPort.queryRecruitmentDetailById(recruitment.getId(), securityPort.getCurrentUserId());
        List<RecruitAreaResponse> recruitAreaResponses = queryRecruitmentPort.queryRecruitAreasByRecruitmentId(
                        recruitment.getId()
                ).stream()
                .map(recruitAreaResponse ->
                        RecruitAreaResponse.builder()
                                .id(recruitAreaResponse.getId())
                                .job(recruitAreaResponse.getJob().stream()
                                        .map(job -> new JobResponse(job.getId(), job.getName()))
                                        .toList())
                                .tech(recruitAreaResponse.getTech().stream()
                                        .map(tech -> new TechResponse(tech.getId(), tech.getName()))
                                        .toList())
                                .hiring(recruitAreaResponse.getHiring())
                                .majorTask(recruitAreaResponse.getMajorTask())
                                .preferentialTreatment(recruitAreaResponse.getPreferentialTreatment())
                                .build()
                ).toList();

        return QueryRecruitmentDetailResponse.of(recruitmentDetail, recruitAreaResponses, getApplicable(recruitmentDetail.isWinterIntern()));
    }

    private Boolean getApplicable(boolean winterIntern) {
        if (securityPort.getCurrentUserAuthority().equals(Authority.STUDENT)
                || securityPort.getCurrentUserAuthority().equals(Authority.DEVELOPER)) {
            return securityPort.getCurrentStudent().getApplicable(winterIntern);
        }
        return null;
    }
}