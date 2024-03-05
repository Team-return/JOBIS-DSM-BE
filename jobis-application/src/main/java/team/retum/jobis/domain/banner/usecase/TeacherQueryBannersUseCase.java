package team.retum.jobis.domain.banner.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.banner.dto.TeacherQueryBannersResponse;
import team.retum.jobis.domain.banner.spi.QueryBannerPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryBannersUseCase {

    private final QueryBannerPort queryBannerPort;

    public TeacherQueryBannersResponse execute(boolean isOpened) {
        return new TeacherQueryBannersResponse(
                queryBannerPort.queryBanners(isOpened)
        );
    }
}
