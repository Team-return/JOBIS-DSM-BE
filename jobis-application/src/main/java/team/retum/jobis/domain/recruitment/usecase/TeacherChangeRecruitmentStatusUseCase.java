package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.domain.recruitment.event.RecruitmentStatusChangedEvent;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.RecruitmentPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class TeacherChangeRecruitmentStatusUseCase {

    private final RecruitmentPort recruitmentPort;
    private final PublishEventPort publishEventPort;

    public void execute(List<Long> recruitmentIds, RecruitStatus status) {
        List<Recruitment> recruitments = recruitmentPort.getAllByIdInOrThrow(recruitmentIds);

        recruitmentPort.saveAll(
            recruitments.stream()
                .map(recruitment -> recruitment.changeStatus(status))
                .toList()
        );
        publishEventPort.publishEvent(new RecruitmentStatusChangedEvent(recruitments));
    }
}
