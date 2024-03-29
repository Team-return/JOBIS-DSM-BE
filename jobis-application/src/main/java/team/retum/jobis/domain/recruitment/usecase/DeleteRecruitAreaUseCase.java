package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.recruitment.checker.RecruitmentChecker;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaCannotDeleteException;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

@RequiredArgsConstructor
@UseCase
public class DeleteRecruitAreaUseCase {
    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final RecruitmentChecker recruitmentChecker;

    public void execute(Long recruitAreaId) {
        RecruitArea recruitArea = queryRecruitmentPort.queryRecruitmentAreaById(recruitAreaId)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);

        checkRecruitmentAreaDeletable(recruitArea);
        recruitmentChecker.checkPermission(recruitArea);

        commandRecruitmentPort.deleteRecruitAreaById(recruitArea.getId());
    }

    private void checkRecruitmentAreaDeletable(RecruitArea recruitArea) {
        if (queryRecruitmentPort.queryRecruitmentAreaCountByRecruitmentId(recruitArea.getRecruitmentId()) <= 1) {
            throw RecruitAreaCannotDeleteException.EXCEPTION;
        }
    }
}
