package team.retum.jobis.domain.banner.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.banner.spi.BannerPort;

@RequiredArgsConstructor
@UseCase
public class UpdateBannerStatusUseCase {

    private final BannerPort bannerPort;

    public void execute() {
        bannerPort.deleteExpiredBanners();
    }
}
