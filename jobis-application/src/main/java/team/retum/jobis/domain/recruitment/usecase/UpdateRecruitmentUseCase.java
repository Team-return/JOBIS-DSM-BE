package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.recruitment.checker.RecruitmentChecker;
import team.retum.jobis.domain.recruitment.dto.request.UpdateRecruitmentRequest;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.RecruitmentPort;

@RequiredArgsConstructor
@UseCase
public class UpdateRecruitmentUseCase {

    private final RecruitmentPort recruitmentPort;
    private final RecruitmentChecker recruitmentChecker;

    public void execute(UpdateRecruitmentRequest request, Long recruitmentId) {
        Recruitment recruitment = recruitmentPort.getByIdOrThrow(recruitmentId);

        recruitmentChecker.checkPermission(recruitment);

        recruitmentPort.save(recruitment.update(request));
    }
}
