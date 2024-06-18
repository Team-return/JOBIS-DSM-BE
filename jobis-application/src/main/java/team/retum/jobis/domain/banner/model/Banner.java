package team.retum.jobis.domain.banner.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

import java.time.LocalDate;

@Getter
@Builder
@Aggregate
public class Banner {

    private final Long id;

    private final String bannerUrl;

    private final BannerType bannerType;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final Long detailId;

}
