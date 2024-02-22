package team.retum.jobis.domain.banner.spi;

import team.retum.jobis.domain.banner.model.Banner;

public interface CommandBannerPort {

    void saveBanner(Banner banner);

    void deleteBanner(Banner banner);
}
