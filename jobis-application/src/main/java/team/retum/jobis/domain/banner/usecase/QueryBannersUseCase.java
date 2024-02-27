package team.retum.jobis.domain.banner.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.banner.dto.QueryBannersResponse;
import team.retum.jobis.domain.banner.spi.QueryBannerPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryBannersUseCase {

    private final QueryBannerPort queryBannerPort;

    public QueryBannersResponse execute() {
        return new QueryBannersResponse(
                queryBannerPort.queryCurrentBanners()
        );
    }
}
