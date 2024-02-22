package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.domain.recruitment.dto.request.ChangeRecruitmentStatusRequest;
import team.retum.jobis.domain.recruitment.event.RecruitmentStatusChangedEvent;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class TeacherChangeRecruitmentStatusUseCase {
    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final PublishEventPort publishEventPort;

    public void execute(List<Long> recruitmentIds, RecruitStatus status) {
        List<Recruitment> recruitments = queryRecruitmentPort.queryRecruitmentsByIdIn(recruitmentIds);
        if (recruitments.size() != recruitmentIds.size()) {
            throw RecruitmentNotFoundException.EXCEPTION;
        }

        commandRecruitmentPort.saveAllRecruitments(
                recruitments.stream()
                        .map(recruitment -> recruitment.changeStatus(status))
                        .toList()
        );
        publishEventPort.publishEvent(new RecruitmentStatusChangedEvent(recruitments));
    }
}
