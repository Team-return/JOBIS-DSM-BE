package team.retum.jobis.domain.banner.spi;

import team.retum.jobis.domain.banner.model.Banner;

import java.util.Optional;

public interface QueryBannerPort {
    Optional<Banner> queryBannerById(Long bannerId);
}
