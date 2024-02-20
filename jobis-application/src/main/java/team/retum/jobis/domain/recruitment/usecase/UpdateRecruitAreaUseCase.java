package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.recruitment.dto.request.CreateRecruitAreaRequest;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;

@RequiredArgsConstructor
@UseCase
public class UpdateRecruitAreaUseCase {
    private final CommandRecruitmentPort commandRecruitmentPort;

    public void execute(CreateRecruitAreaRequest request, Long recruitAreaId) {
        commandRecruitmentPort.saveRecruitmentArea(
                RecruitArea.of(request, recruitAreaId)
        );
    }
}