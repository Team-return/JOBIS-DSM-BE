package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.domain.recruitment.event.RecruitmentStatusChangedEvent;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.RecruitmentPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ChangeRecruitmentStatusSchedulerUseCase {

    private final RecruitmentPort recruitmentPort;
    private final PublishEventPort eventPublisher;

    public void execute() {
        List<Recruitment> recruitments = recruitmentPort.getAll();

        recruitmentPort.saveAll(
            recruitments.stream()
                .map(Recruitment::updateRecruitmentStatus)
                .toList()
        );
        eventPublisher.publishEvent(new RecruitmentStatusChangedEvent(recruitments));
    }
}
