package team.retum.jobis.domain.banner.spi;

import team.retum.jobis.domain.banner.model.Banner;
import team.retum.jobis.domain.banner.spi.vo.BannerVO;

import java.util.List;
import java.util.Optional;

public interface QueryBannerPort {
    Optional<Banner> queryBannerById(Long bannerId);

    List<BannerVO> queryCurrentBanners();
}
