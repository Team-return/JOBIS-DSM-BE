package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.recruitment.dto.request.CreateRecruitAreaRequest;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.service.CheckRecruitmentPermissionService;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

@RequiredArgsConstructor
@UseCase
public class UpdateRecruitAreaUseCase {
    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final CheckRecruitmentPermissionService checkRecruitmentPermissionService;

    public void execute(CreateRecruitAreaRequest request, Long recruitAreaId) {
        RecruitArea recruitArea = queryRecruitmentPort.queryRecruitmentAreaById(recruitAreaId)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);
        checkRecruitmentPermissionService.checkPermission(recruitArea);

        commandRecruitmentPort.deleteRecruitAreaById(recruitAreaId);
        commandRecruitmentPort.saveRecruitmentArea(
                RecruitArea.of(request, recruitArea.getRecruitmentId())
        );
    }
}