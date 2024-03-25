package team.retum.jobis.domain.banner.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import team.retum.jobis.domain.banner.dto.request.CreateBannerRequest;
import team.retum.jobis.domain.banner.model.BannerType;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CreateBannerWebRequest {

    @NotNull
    private String bannerUrl;

    @NotNull
    private BannerType bannerType;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    public CreateBannerRequest toDomainRequest() {
        return CreateBannerRequest.builder()
            .bannerUrl(this.bannerUrl)
            .bannerType(this.bannerType)
            .startDate(this.startDate)
            .endDate(this.endDate)
            .build();
    }
}
