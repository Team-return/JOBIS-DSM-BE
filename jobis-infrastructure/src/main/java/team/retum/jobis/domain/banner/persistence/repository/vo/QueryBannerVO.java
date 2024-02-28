package team.retum.jobis.domain.banner.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.banner.model.BannerType;
import team.retum.jobis.domain.banner.spi.vo.BannerVO;

@Getter
public class QueryBannerVO extends BannerVO {

    @QueryProjection
    public QueryBannerVO(Long id, String bannerUrl, BannerType bannerType) {
        super(id, bannerUrl, bannerType);
    }
}
