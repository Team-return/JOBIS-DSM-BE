package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.service.CheckRecruitmentPermissionService;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

@RequiredArgsConstructor
@UseCase
public class DeleteRecruitmentUseCase {
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final CommandRecruitmentPort commandRecruitmentPort;
    private final CheckRecruitmentPermissionService checkRecruitmentPermissionService;

    public void execute(Long recruitmentId) {
        Recruitment recruitment = queryRecruitmentPort.queryRecruitmentById(recruitmentId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
        recruitment.checkIsDeletable();
        checkRecruitmentPermissionService.checkPermission(recruitment);

        commandRecruitmentPort.deleteRecruitment(recruitment);
    }
}
