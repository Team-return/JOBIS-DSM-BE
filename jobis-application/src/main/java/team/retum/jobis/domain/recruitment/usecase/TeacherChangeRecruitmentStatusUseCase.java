package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.recruitment.dto.request.ChangeRecruitmentStatusRequest;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class TeacherChangeRecruitmentStatusUseCase {
    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryRecruitmentPort queryRecruitmentPort;

    public void execute(ChangeRecruitmentStatusRequest request) {
        List<Recruitment> recruitments = queryRecruitmentPort.queryRecruitmentsByIdIn(request.getRecruitmentIds());
        if (recruitments.size() != request.getRecruitmentIds().size()) {
            throw RecruitmentNotFoundException.EXCEPTION;
        }

        commandRecruitmentPort.saveAllRecruitments(
                recruitments.stream()
                        .map(recruitment -> recruitment.changeStatus(request.getStatus()))
                        .toList()
        );
    }
}
