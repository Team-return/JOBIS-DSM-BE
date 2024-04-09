package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.dto.response.RecruitAreaResponse;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitAreaPort;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryRecruitmentDetailUseCase {

    private static final List<Authority> APPLICABLE_AUTHORITIES = List.of(Authority.STUDENT, Authority.DEVELOPER);
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final QueryRecruitAreaPort queryRecruitAreaPort;
    private final QueryApplicationPort queryApplicationPort;
    private final SecurityPort securityPort;

    public QueryRecruitmentDetailResponse execute(Long recruitId) {
        Recruitment recruitment = queryRecruitmentPort.getByIdOrThrow(recruitId);

        RecruitmentDetailVO recruitmentDetail =
            queryRecruitmentPort.getByIdAndStudentIdOrThrow(recruitment.getId(), securityPort.getCurrentUserId());

        List<RecruitAreaResponse> recruitAreaResponses =
            queryRecruitAreaPort.getByRecruitmentId(recruitment.getId())
                .stream()
                .map(RecruitAreaResponse::from)
                .toList();

        return QueryRecruitmentDetailResponse.of(
            recruitmentDetail,
            recruitAreaResponses,
            getApplicable(recruitmentDetail.isWinterIntern(), recruitment.getId()),
            checkAuthority()
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

    private boolean checkAuthority() {
        return securityPort.getCurrentUserAuthority().equals(Authority.STUDENT);
    }
}
