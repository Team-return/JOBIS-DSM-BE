package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.recruitment.dto.request.CreateRecruitAreaRequest;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.service.CheckRecruitmentPermissionService;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

@RequiredArgsConstructor
@UseCase
public class CreateRecruitAreaUseCase {
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final CommandRecruitmentPort commandRecruitmentPort;
    private final CheckRecruitmentPermissionService checkRecruitmentPermissionService;

    public void execute(CreateRecruitAreaRequest request, Long recruitmentId) {
        Recruitment recruitment = queryRecruitmentPort.queryRecruitmentById(recruitmentId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
        checkRecruitmentPermissionService.checkPermission(recruitment);
        commandRecruitmentPort.saveRecruitmentArea(RecruitArea.of(request, recruitmentId));
    }
}
