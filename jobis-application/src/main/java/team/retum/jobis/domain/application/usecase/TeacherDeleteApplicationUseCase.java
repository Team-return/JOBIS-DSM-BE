package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class TeacherDeleteApplicationUseCase {

    private final CommandApplicationPort commandApplicationPort;

    public void execute(List<Long> applicationIds) {
        commandApplicationPort.deleteByIds(applicationIds);
    }
}
