package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class TeacherDeleteApplicationUseCase {

    private final CommandApplicationPort commandApplicationPort;
    private final QueryApplicationPort queryApplicationPort;

    public void execute(List<Long> applicationIds) {
        queryApplicationPort.getAllByIdInOrThrow(applicationIds);

        commandApplicationPort.deleteByIds(applicationIds);
    }
}
