package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.recruitment.checker.RecruitmentChecker;
import team.retum.jobis.domain.recruitment.dto.request.CreateRecruitAreaRequest;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.spi.RecruitAreaPort;

@RequiredArgsConstructor
@UseCase
public class UpdateRecruitAreaUseCase {

    private final RecruitAreaPort recruitAreaPort;
    private final RecruitmentChecker recruitmentChecker;

    public void execute(CreateRecruitAreaRequest request, Long recruitAreaId) {
        RecruitArea recruitArea = recruitAreaPort.getByIdOrThrow(recruitAreaId);

        recruitmentChecker.checkPermission(recruitArea);

        recruitAreaPort.save(recruitArea.update(request));
    }
}
