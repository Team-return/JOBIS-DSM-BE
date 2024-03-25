package team.retum.jobis.domain.banner.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.banner.model.BannerType;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class TeacherBannersVO {

    private final Long id;
    private final String bannerUrl;
    private final BannerType bannerType;
    private final LocalDate startDate;
    private final LocalDate endDate;
}
