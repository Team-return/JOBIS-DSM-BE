package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.recruitment.checker.RecruitmentChecker;
import team.retum.jobis.domain.recruitment.dto.request.CreateRecruitAreaRequest;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitAreaPort;
import team.retum.jobis.domain.recruitment.spi.RecruitmentPort;

@RequiredArgsConstructor
@UseCase
public class CreateRecruitAreaUseCase {

    private final RecruitmentPort recruitmentPort;
    private final CommandRecruitAreaPort commandRecruitAreaPort;
    private final RecruitmentChecker recruitmentChecker;

    public void execute(CreateRecruitAreaRequest request, Long recruitmentId) {
        Recruitment recruitment = recruitmentPort.getByIdOrThrow(recruitmentId);
        recruitmentChecker.checkPermission(recruitment);

        commandRecruitAreaPort.save(RecruitArea.of(request, recruitmentId));
    }
}
