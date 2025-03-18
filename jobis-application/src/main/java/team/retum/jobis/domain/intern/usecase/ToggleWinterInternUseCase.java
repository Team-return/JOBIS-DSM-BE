package team.retum.jobis.domain.intern.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.domain.intern.event.WinterInternToggledEvent;
import team.retum.jobis.domain.intern.model.WinterIntern;
import team.retum.jobis.domain.intern.spi.CommandWinterInternPort;
import team.retum.jobis.domain.intern.spi.QueryWinterInternPort;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.user.spi.QueryUserPort;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ToggleWinterInternUseCase {

    private final CommandWinterInternPort commandWinterInternPort;
    private final QueryWinterInternPort queryWinterInternPort;
    private final PublishEventPort publishEventPort;
    private final QueryUserPort queryUserPort;

    public void execute() {
        boolean toggled = !queryWinterInternPort.getIsWinterIntern();

        WinterIntern toggledWinterIntern = WinterIntern.builder()
            .isWinterInterned(toggled)
            .build();

        commandWinterInternPort.save(toggledWinterIntern);

        List<String> deviceTokens = queryUserPort.getDeviceTokenByTopic(Topic.WINTER_INTERN);

        publishEventPort.publishEvent(new WinterInternToggledEvent(
            toggledWinterIntern,
            0L,
            Topic.WINTER_INTERN,
            deviceTokens
        ));
    }
}
