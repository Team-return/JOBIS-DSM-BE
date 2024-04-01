package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.dto.response.RecruitAreaResponse;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryRecruitmentDetailUseCase {

    private static final List<Authority> APPLICABLE_AUTHORITIES = List.of(Authority.STUDENT, Authority.DEVELOPER);
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final QueryApplicationPort queryApplicationPort;
    private final SecurityPort securityPort;

    public QueryRecruitmentDetailResponse execute(Long recruitId) {
        Recruitment recruitment = queryRecruitmentPort.queryRecruitmentById(recruitId)
            .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        RecruitmentDetailVO recruitmentDetail =
            queryRecruitmentPort.queryRecruitmentDetailByIdAndStudentId(recruitment.getId(), securityPort.getCurrentUserId());

        List<RecruitAreaResponse> recruitAreaResponses =
            queryRecruitmentPort.queryRecruitAreasByRecruitmentId(recruitment.getId())
                .stream()
                .map(RecruitAreaResponse::from)
                .toList();

        return QueryRecruitmentDetailResponse.of(
            recruitmentDetail,
            recruitAreaResponses,
            getApplicable(recruitmentDetail.isWinterIntern(), recruitment.getId())
        );
    }

    private Boolean getApplicable(boolean winterIntern, long recruitmentId) {
        if (APPLICABLE_AUTHORITIES.contains(securityPort.getCurrentUserAuthority())) {
            if (queryApplicationPort.existsApplicationByStudentIdAndRecruitmentId(
                securityPort.getCurrentUserId(), recruitmentId)
            ) {
                return false;
            }
            return securityPort.getCurrentStudent().getApplicable(winterIntern);
        }
        return null;
    }
}
