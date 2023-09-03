package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.NotificationPublish;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.notification.model.Topic;
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

    @NotificationPublish(topic = Topic.RECRUITMENT_DONE)
    public List<Recruitment> execute() {
        List<Recruitment> recruitments = queryRecruitmentPort.queryAllRecruitments();

        commandRecruitmentPort.saveAllRecruitments(
                recruitments.stream()
                        .filter(recruitment -> recruitment.getStartDate().isAfter(LocalDate.now()))
                        .map(recruitment -> recruitment.changeStatus(RecruitStatus.RECRUITING))
                        .filter(recruitment -> recruitment.getEndDate().isAfter(LocalDate.now()))
                        .map(recruitment -> recruitment.changeStatus(RecruitStatus.DONE))
                        .toList()
        );

        return recruitments;
    }
}
