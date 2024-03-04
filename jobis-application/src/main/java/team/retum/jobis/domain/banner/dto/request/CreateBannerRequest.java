package team.retum.jobis.domain.banner.dto.request;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.banner.model.BannerType;

import java.time.LocalDate;

@Getter
@Builder
public class CreateBannerRequest {

    private String bannerUrl;

    private BannerType bannerType;

    private LocalDate startDate;

    private LocalDate endDate;
}
