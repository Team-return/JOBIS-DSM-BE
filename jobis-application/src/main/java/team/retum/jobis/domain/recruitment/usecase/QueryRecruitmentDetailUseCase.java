package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.recruitment.dto.response.QueryRecruitmentDetailResponse;
import team.retum.jobis.domain.recruitment.dto.response.RecruitAreaResponse;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.model.UnapplicableReason;
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
            queryRecruitAreaPort.getAllByRecruitmentId(recruitment.getId())
                .stream()
                .map(RecruitAreaResponse::from)
                .toList();

        boolean applicableAuthority = APPLICABLE_AUTHORITIES.contains(securityPort.getCurrentUserAuthority());
        UnapplicableReason unapplicableReason = applicableAuthority
            ? getUnapplicableReason(recruitment, recruitmentDetail.isWinterIntern())
            : null;

        return QueryRecruitmentDetailResponse.of(
            recruitmentDetail,
            recruitAreaResponses,
            applicableAuthority ? unapplicableReason == null : null,
            unapplicableReason,
            checkAuthority()
        );
    }

    private UnapplicableReason getUnapplicableReason(Recruitment recruitment, boolean winterIntern) {
        Long studentId = securityPort.getCurrentUserId();

        if (queryApplicationPort.existsByStudentIdAndRecruitmentId(studentId, recruitment.getId())) {
            return UnapplicableReason.ALREADY_APPLIED;
        }

        if (recruitment.getStatus() != RecruitStatus.RECRUITING) {
            return UnapplicableReason.NOT_RECRUITING;
        }

        if (queryApplicationPort.existsByStudentIdAndApplicationStatusIn(studentId, ApplicationStatus.DUPLICATE_CHECK)) {
            return UnapplicableReason.HAS_ACTIVE_APPLICATION;
        }

        if (!securityPort.getCurrentStudent().getApplicable(winterIntern)) {
            return UnapplicableReason.GRADE_NOT_ELIGIBLE;
        }

        return null;
    }

    private boolean checkAuthority() {
        return securityPort.getCurrentUserAuthority().equals(Authority.STUDENT);
    }
}
