package team.retum.jobis.domain.banner.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.banner.model.BannerType;

@Getter
@AllArgsConstructor
public class BannerVO {

    private final Long id;
    private final String title;
    private final String content;
    private final BannerType bannerType;
    private final Long detailId;
}
