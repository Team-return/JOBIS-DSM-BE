package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaCannotDeleteException;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

@RequiredArgsConstructor
@UseCase
public class DeleteRecruitAreaUseCase {

    private final SecurityPort securityPort;
    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryRecruitmentPort queryRecruitmentPort;

    public void execute(Long recruitAreaId) {
        RecruitArea recruitArea = queryRecruitmentPort.queryRecruitmentAreaById(recruitAreaId)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);

        Authority currentUserAuthority = securityPort.getCurrentUserAuthority();
        if (currentUserAuthority == Authority.COMPANY) {
            Long currentUserId = securityPort.getCurrentUserId();
            queryRecruitmentPort.queryRecruitmentById(recruitArea.getRecruitmentId())
                    .get().checkCompany(currentUserId);
        }

        Long recruitAreaCount =
                queryRecruitmentPort.queryRecruitmentAreaCountByRecruitmentId(recruitArea.getRecruitmentId());
        if (recruitAreaCount <= 1) {
            throw RecruitAreaCannotDeleteException.EXCEPTION;
        }

        commandRecruitmentPort.deleteRecruitAreaById(recruitArea.getId());
    }
}
