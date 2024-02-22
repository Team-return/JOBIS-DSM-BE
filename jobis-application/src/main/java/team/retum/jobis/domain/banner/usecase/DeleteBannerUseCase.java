package team.retum.jobis.domain.banner.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.banner.spi.CommandBannerPort;

@RequiredArgsConstructor
@UseCase
public class DeleteBannerUseCase {

    private final CommandBannerPort commandBannerPort;

    public void execute(Long bannerId) {
        commandBannerPort.deleteBannerById(bannerId);
    }
}
