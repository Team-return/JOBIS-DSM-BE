package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.recruitment.checker.RecruitmentChecker;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.RecruitmentPort;

@RequiredArgsConstructor
@UseCase
public class DeleteRecruitmentUseCase {

    private final RecruitmentPort recruitmentPort;
    private final RecruitmentChecker recruitmentChecker;

    public void execute(Long recruitmentId) {
        Recruitment recruitment = recruitmentPort.getByIdOrThrow(recruitmentId);
        recruitment.checkIsDeletable();
        recruitmentChecker.checkPermission(recruitment);

        recruitmentPort.delete(recruitment);
    }
}
