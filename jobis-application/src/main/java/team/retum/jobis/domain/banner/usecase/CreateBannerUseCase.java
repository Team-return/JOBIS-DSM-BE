package team.retum.jobis.domain.banner.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.banner.dto.CreateBannerRequest;
import team.retum.jobis.domain.banner.model.Banner;
import team.retum.jobis.domain.banner.spi.CommandBannerPort;

@RequiredArgsConstructor
@UseCase
public class CreateBannerUseCase {

    private final CommandBannerPort commandBannerPort;

    public void execute(CreateBannerRequest request) {
        commandBannerPort.saveBanner(
                Banner.builder()
                        .bannerUrl(request.getBannerUrl())
                        .bannerType(request.getBannerType())
                        .startDate(request.getStartDate())
                        .endDate(request.getEndDate())
                        .build()
        );
    }
}