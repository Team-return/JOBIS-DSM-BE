package team.retum.jobis.domain.banner.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.banner.exception.BannerNotFoundException;
import team.retum.jobis.domain.banner.model.Banner;
import team.retum.jobis.domain.banner.spi.CommandBannerPort;
import team.retum.jobis.domain.banner.spi.QueryBannerPort;

@RequiredArgsConstructor
@UseCase
public class DeleteBannerUseCase {

    private final CommandBannerPort commandBannerPort;
    private final QueryBannerPort queryBannerPort;

    public void execute(Long bannerId) {
        Banner banner = queryBannerPort.queryBannerById(bannerId)
                        .orElseThrow(() -> BannerNotFoundException.EXCEPTION);

        commandBannerPort.deleteBanner(banner);
    }
}
