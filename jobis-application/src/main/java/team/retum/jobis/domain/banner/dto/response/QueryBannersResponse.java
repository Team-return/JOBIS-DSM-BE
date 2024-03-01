package team.retum.jobis.domain.banner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.banner.model.BannerType;
import team.retum.jobis.domain.banner.spi.vo.BannerVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryBannersResponse {

    private final List<BannerVO> banners;
}
