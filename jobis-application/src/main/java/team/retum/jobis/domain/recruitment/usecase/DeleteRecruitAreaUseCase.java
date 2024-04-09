package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.recruitment.checker.RecruitmentChecker;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaCannotDeleteException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.spi.RecruitAreaPort;

@RequiredArgsConstructor
@UseCase
public class DeleteRecruitAreaUseCase {

    private final RecruitAreaPort recruitAreaPort;
    private final RecruitmentChecker recruitmentChecker;

    public void execute(Long recruitAreaId) {
        RecruitArea recruitArea = recruitAreaPort.getByIdOrThrow(recruitAreaId);

        checkRecruitmentAreaDeletable(recruitArea);
        recruitmentChecker.checkPermission(recruitArea);

        recruitAreaPort.delete(recruitArea);
    }

    private void checkRecruitmentAreaDeletable(RecruitArea recruitArea) {
        if (recruitAreaPort.getCountByRecruitmentId(recruitArea.getRecruitmentId()) <= 1) {
            throw RecruitAreaCannotDeleteException.EXCEPTION;
        }
    }
}
