package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.domain.recruitment.event.ChangeRecruitmentStatusEvent;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ChangeRecruitmentStatusSchedulerUseCase {
    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final PublishEventPort publishEventPort;

    public void execute() {
        List<Recruitment> recruitments = queryRecruitmentPort.queryAllRecruitments();

        commandRecruitmentPort.saveAllRecruitments(
                recruitments.stream()
                        .map(Recruitment::updateRecruitmentStatus)
                        .toList()
        );
        publishEventPort.publishEvent(new ChangeRecruitmentStatusEvent(recruitments));
    }
}
