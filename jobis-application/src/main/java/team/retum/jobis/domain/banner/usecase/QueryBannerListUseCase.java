package team.retum.jobis.domain.banner.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.banner.dto.QueryBannerListResponse;
import team.retum.jobis.domain.banner.spi.QueryBannerPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryBannerListUseCase {

    private final QueryBannerPort queryBannerPort;

    public QueryBannerListResponse execute(String bannerStatus) {
        return new QueryBannerListResponse(
                queryBannerPort.queryBannerList(bannerStatus)
        );
    }
}
