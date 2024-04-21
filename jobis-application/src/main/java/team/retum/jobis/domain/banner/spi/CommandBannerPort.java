package team.retum.jobis.domain.banner.spi;

import team.retum.jobis.domain.banner.model.Banner;

public interface CommandBannerPort {

    void save(Banner banner);

    void delete(Banner banner);
}
